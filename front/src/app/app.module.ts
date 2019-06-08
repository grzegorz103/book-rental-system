import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookService } from './services/book-service';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { UserCreateComponent } from './components/user-create/user-create.component';
import { UserService } from './services/user.service';
import { FormsModule } from '@angular/forms'
import { AuthService } from './services/auth.service';
import { UserLoginComponent } from './components/user-login/user-login.component';


@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    NavbarComponent,
    UserCreateComponent,
    UserLoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule,
    FormsModule
  ],
  providers: [BookService, UserService, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
