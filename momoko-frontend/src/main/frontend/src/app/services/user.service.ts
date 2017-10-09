import {Injectable} from '@angular/core';
import {User} from '../dtos/user';
import {Http} from '@angular/http';

import {Observable} from 'rxjs/Observable';
import {API_URL} from '../app.component';

@Injectable()
export class UserService {

    constructor(private http: Http) {
    }

    getUsers(): Observable<User[]> {
        return this.http.get(API_URL + '/users')
            .map(response => response.json() as User[]);
    }

    getUser(userId: string): Observable<User> {
        return this.http.get(API_URL + '/user/' + userId)
            .map(response => response.json());
    }

}
