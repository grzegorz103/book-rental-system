import { Component, OnInit } from '@angular/core';
import { Book } from '../../models/book';
import { BookService } from '../../services/book-service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books: Book[];

  constructor(
    private bookService: BookService
  ) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData(): void{
    this.bookService.findAll().subscribe(res => this.books = res.filter(e => !e.borrowed));
  }

}
