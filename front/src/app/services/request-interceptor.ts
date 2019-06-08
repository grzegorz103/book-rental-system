import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

    constructor(public auth: AuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let token = this.auth.getToken();
        if (token !== '') {
            req = req.clone({
                setHeaders: {
                    Authorization: 'Basic ' + token
                }
            });
        }
        return next.handle(req);
    }

}
