import { Component, OnInit } from '@angular/core';
import { BookService } from '../../services/book-service';
import { Book } from '../../models/book';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {

  book: Book;

  constructor(
    private bookService: BookService,
    private router: Router
  ) {
    this.book = new Book();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.bookService.create(this.book).subscribe(res => this.router.navigate(['/books']))
  }

}
