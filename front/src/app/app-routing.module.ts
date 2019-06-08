import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookListComponent } from './components/book-list/book-list.component';
import { UserCreateComponent } from './components/user-create/user-create.component';
import { UserLoginComponent } from './components/user-login/user-login.component';

const routes: Routes = [
  { path: 'books', component: BookListComponent },
  { path: 'register', component: UserCreateComponent },
  {path: 'login', component:UserLoginComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
export class AppRoutingModule { }
