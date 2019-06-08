import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { BookListComponent } from './book-list/book-list.component';
import { BookService } from './services/book-service';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { UserCreateComponent } from './user-create/user-create.component';


@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    NavbarComponent,
    UserCreateComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule
  ],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule { }
