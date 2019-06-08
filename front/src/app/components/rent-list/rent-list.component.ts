import { Component, OnInit } from '@angular/core';
import { Rental } from '../../models/rental';
import { RentalService } from '../../services/rental.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-rent-list',
  templateUrl: './rent-list.component.html',
  styleUrls: ['./rent-list.component.css']
})
export class RentListComponent implements OnInit {

  rentals: Rental[];
  penalty: boolean;

  constructor(
    private rentalService: RentalService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.rentalService.findAll().subscribe(res => {
      this.rentals = res.filter(e => e.user.username === this.authService.getUsername());
      if (this.rentals.find(e => e.penalty > 0)) {
        this.penalty = true;
      }
    });
  }

  returnBook(id: number) {
    this.rentalService.returnBook(id).subscribe(res => this.fetchData());
  }
}