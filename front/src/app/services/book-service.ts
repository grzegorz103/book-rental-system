import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Book } from '../models/book';
@Injectable()
export class BookService {

  bookUrl: string;

  constructor(
    private http: HttpClient
  ) {
    this.bookUrl = '/api/book/'
  }

  public findAll() {
    return this.http.get<Book[]>(this.bookUrl);
  }

  public create(book: Book){
    return this.http.post<Book>(this.bookUrl, book);
  }

}
