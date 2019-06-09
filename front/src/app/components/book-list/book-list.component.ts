import { Component, OnInit } from '@angular/core';
import { Book } from '../../models/book';
import { BookService } from '../../services/book-service';
import { RentalService } from '../../services/rental.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books: Book[];
  penalty: boolean;

  constructor(
    private bookService: BookService,
    private rentalService: RentalService,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData(): void {
    this.bookService.findAll().subscribe(res => this.books = res.filter(e => !e.borrowed));
    this.rentalService.findAll().subscribe(res => {
      if (res.filter(e => e.user.username === this.authService.getUsername()).find(e => e.penalty > 0)) {
        this.penalty = true;
      }
    });
  }

  rentBook(id: number) {
    this.rentalService.create(id).subscribe(res => {
      alert('Thank you for renting the book!');
      this.router.navigate(['/rent']);
    });
  }
}
