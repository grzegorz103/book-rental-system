import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];

  constructor(
    private userService: UserService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.userService.findAll().subscribe(res => this.users = res);
  }

  delete(id: number) {
    if (this.authService.getUsername() === this.users.find(e => e.id === id).username) {
      alert('You can\'t remove yourself!');
      return;
    }
    this.userService.delete(id).subscribe(res => this.fetchData());
  }

}
