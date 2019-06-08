import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookListComponent } from './components/book-list/book-list.component';
import { UserCreateComponent } from './components/user-create/user-create.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { RentListComponent } from './components/rent-list/rent-list.component';
import { BookFormComponent } from './components/book-form/book-form.component';
import { UserListComponent } from './components/user-list/user-list.component';

const routes: Routes = [
  { path: 'books', component: BookListComponent },
  { path: 'register', component: UserCreateComponent },
  { path: 'login', component: UserLoginComponent },
  { path: 'rent', component: RentListComponent },
  { path: 'books/add', component: BookFormComponent },
  { path: 'users', component: UserListComponent },
  { path: '**', component: UserLoginComponent }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
export class AppRoutingModule { }
