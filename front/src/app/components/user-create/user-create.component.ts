import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  user: User;

  constructor(private userService: UserService,
    private router: Router) {
    this.user = new User();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.create(this.user).subscribe(result => {
      alert('Thank you for registering!');
      this.router.navigate(['/login'])
    });
  }
}
