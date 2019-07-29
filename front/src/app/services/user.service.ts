import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserService {


  userUrl: string;

  constructor(
    private http: HttpClient
  ) {
    this.userUrl = '/api/user/';
  }

  public findAll() {
    return this.http.get<User[]>(this.userUrl);
  }

  public delete(id: number) {
    return this.http.delete(this.userUrl + id);
  }

  public create(user: User) {
    return this.http.post<User>(this.userUrl, user);
  }

  public isLoginCorrect(username: string, password: string) {
    return this.http.post<Observable<boolean>>('/api/user/login', {
      username: username,
      password: password,
      passwordConfirm: password
    });
  }

}
