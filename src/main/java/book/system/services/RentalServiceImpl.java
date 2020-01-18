package book.system.services;

import book.system.dto.RentalDTO;
import book.system.mappers.BookMapper;
import book.system.mappers.RentalMapper;
import book.system.models.Book;
import book.system.models.Rental;
import book.system.models.User;
import book.system.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("rentalService")
public class RentalServiceImpl implements RentalService {
    private final BookService bookService;

    private final RentalRepository rentalRepository;

    private final float FIRST_WEEK_PENALTY = 2.00f;

    private final int RENT_PERIOD = 14;

    private final float DAILY_PENALTY = 0.5f;

    private final BookMapper bookMapper;

    private final RentalMapper rentalMapper;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository,
                             BookService bookService,
                             BookMapper bookMapper,
                             RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.rentalMapper = rentalMapper;
    }

    /**
     * Returns list of all rents mapped to dto
     *
     * @return list of rents
     */
    @Override
    public List<RentalDTO> findAll() {
        return rentalRepository.findAll()
            .stream()
            .map(rentalMapper::rentalToDTO)
            .collect(Collectors.toList());
    }

    /**
     * This method creates new rent for given book as parameter and updates
     * that book's status to borrowed.
     * User has two weeks to return borrowed book.
     *
     * @param book book that rent will be created for
     * @return created rent
     * @throws RuntimeException when user has penalty, or the book is already borrowed
     */
    @Override
    @Transactional
    public RentalDTO create(Book book) {
        if (hasPenalty(getCurrentUser()))
            throw new RuntimeException("You can't borrow a book because you have a penalty");
        if (book != null) {
            if (book.isBorrowed())
                throw new RuntimeException("You can't rent a borrowed book");

            book.setBorrowed(true);
            bookService.update(bookMapper.BookToDTO(book));
            LocalDate actualDate = LocalDate.now();
            return rentalMapper.rentalToDTO(rentalRepository.save(
                Rental.builder()
                    .book(book)
                    .rentalDate(actualDate)
                    .returnDate(actualDate.plusDays(RENT_PERIOD))
                    .returned(false)
                    .user(getCurrentUser())
                    .penalty(0f)
                    .build()
                )
            );
        }
        return null;
    }

    /**
     * This method allows users to return rented book
     * If user exceeded the return date, the rent will get calculated penalty
     * for first week 2zł, and 0.5zł for each day after that period
     *
     * @param rental rental for book that will be returned
     * @return rental mapped to dto
     * @throws RuntimeException when rental is null
     */
    @Override
    public RentalDTO returnBook(Rental rental) {
        if (rental != null) {
            if (!rental.isReturned()) {
                Book rentBook = rental.getBook();
                rentBook.setBorrowed(false);
                bookService.update(bookMapper.BookToDTO(rentBook));
                rental.setReturned(true);

                LocalDate actualDate = LocalDate.now();
                float penalty = 0;
                if (actualDate.isAfter(rental.getReturnDate())) {
                    long days = Duration.between(rental.getReturnDate().atStartOfDay(), actualDate.atStartOfDay()).toDays();
                    if (days > 0) {
                        penalty = days <= 7
                            ? FIRST_WEEK_PENALTY
                            : FIRST_WEEK_PENALTY + (days - 7) * DAILY_PENALTY;
                    }
                }
                rental.setPenalty(penalty);
            }
            return rentalMapper.rentalToDTO(rentalRepository.save(rental));
        } else {
            throw new RuntimeException("Incorrent rental!");
        }
    }

    /**
     * Checks whether given user has penalty.
     *
     * @param user user to check penalty
     * @return true if user has penalty, otherwise false
     */
    private boolean hasPenalty(User user) {
        return rentalRepository.findAllByUser(user)
            .stream()
            .filter(e -> !e.isReturned())
            .anyMatch(e -> LocalDate.now().isAfter(e.getReturnDate()));
    }

    /**
     * @return currently logged in user
     */
    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
    }

}
