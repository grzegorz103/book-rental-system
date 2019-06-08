import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookService } from './services/book-service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { UserCreateComponent } from './components/user-create/user-create.component';
import { UserService } from './services/user.service';
import { FormsModule } from '@angular/forms'
import { AuthService } from './services/auth.service';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { RequestInterceptor } from './services/request-interceptor';
import { RentListComponent } from './components/rent-list/rent-list.component';
import { RentalService } from './services/rental.service';


@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    NavbarComponent,
    UserCreateComponent,
    UserLoginComponent,
    RentListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule,
    FormsModule
  ],
  providers: [BookService, UserService, AuthService,RentalService, {
    provide: HTTP_INTERCEPTORS,
    useClass: RequestInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
