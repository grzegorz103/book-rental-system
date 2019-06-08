import { Injectable } from '@angular/core';
import { Rental } from '../models/rental';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class RentalService {

  rentalUrl: string;

  constructor(private http: HttpClient) {
    this.rentalUrl = 'http://localhost:8080/api/rent/';
  }

  public findAll() {
    return this.http.get<Rental[]>(this.rentalUrl);
  }

  public create(id: number) {
    return this.http.get<Rental>(this.rentalUrl + id);
  }

  public returnBook(id: number) {
    return this.http.get<Rental>(this.rentalUrl + 'return/' + id);
  }
}
