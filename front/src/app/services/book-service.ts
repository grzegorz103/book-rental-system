import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Book } from '../models/book';
@Injectable()
export class BookService {

  bookUrl: string;

  constructor(
    private http: HttpClient
  ) {
    this.bookUrl = 'http://localhost:8080/api/book/'
  }

  public findAll() {
    return this.http.get<Book[]>(this.bookUrl);
  }

}
