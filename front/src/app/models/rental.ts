import { Book } from "./book";
import { User } from "./user";

export class Rental {
     id: number;
     book: Book;
     rentalDate: Date;
     returnDate: Date;
     returned: boolean;
     penalty: number;
     user: User;
}
