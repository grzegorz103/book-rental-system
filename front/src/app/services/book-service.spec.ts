import { TestBed, inject } from '@angular/core/testing';

import { BookService } from './book-service';

describe('BookServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BookService]
    });
  });

  it('should be created', inject([BookService], (service: BookService) => {
    expect(service).toBeTruthy();
  }));
});
