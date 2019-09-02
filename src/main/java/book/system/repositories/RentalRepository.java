package book.system.repositories;

import book.system.models.Rental;
import book.system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long>
{
        List<Rental> findAllByUser ( User user );
}
