import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import { BookListComponent } from './book-list/book-list.component';

const routes: Routes =[
 { path: 'books', component: BookListComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
export class AppRoutingModule { }
