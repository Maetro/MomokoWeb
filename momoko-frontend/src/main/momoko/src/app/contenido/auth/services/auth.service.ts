
import {throwError as observableThrowError,  Observable ,  Observer ,  BehaviorSubject, of } from 'rxjs';

import {catchError,  map } from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { Http, RequestOptions } from '@angular/http';
import { Headers } from '@angular/http';
import { Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { NewUser, SignupStatus, LoginStatus, Login } from '../dtos/login';
import { environment } from '../../../../environments/environment';

@Injectable()
export class AuthService {

  log = environment.log;

  isLoggedIn = new BehaviorSubject<boolean>(this.checkCredentials());


  user = { name: 'Guest' };
  redirectUrl: string;
  singUpUrl = environment.singUpURL;
  oauthTokenUrl = environment.oauthTokenUrl;
  accountTokenUrl = environment.accountTokenUrl;
  headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http, private router: Router) {
  }



  signup(newUser: NewUser): Promise<SignupStatus> {
    const options = new RequestOptions({ headers: this.headers });

    return this.http.post(this.singUpUrl, newUser, options)
      .toPromise()
      .then(res => res.json())
      .catch((error: any) => observableThrowError(error.json().error || 'Server error'));
  }

  login(login: Login): Observable<LoginStatus> {
    const params = new URLSearchParams();
    if (this.log) {
      console.log('Login');
    }
    params.append('username', login.usuarioEmail);
    params.append('password', login.usuarioContrasena);
    params.append('expirationMillis', '864000000');
    const headers = new Headers({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'
    });
    const options = new RequestOptions({ headers: headers });

    return this.http.post(this.oauthTokenUrl, params.toString(), options).pipe(
      map(res => {
        console.log('RESPONSE LOGIN');
        this.saveToken(res.json());
        return new LoginStatus('SUCCESS', 'Login Successful');
      }),
      catchError((error: any) => {
        return of(new LoginStatus('FAILURE', 'Username or password is incorrect. Please try again!'));
      }));
  }

  saveToken(token: any) {
    if (this.log) {
      console.log('Obteniendo token de autenticacion');
      console.log(token);
    }
    const expireDate = new Date().getTime() + (24000 * token.get('expires'));
    Cookie.set('access_token', token.get('momoko-authorization'), expireDate);
    Cookie.set('role', token.role);
    this.changeLoginStatus(true);
    debugger;
    let urlDestination = '/';
    if (this.redirectUrl != null){
      urlDestination = this.redirectUrl;
    }
    if (token.role === 'ROLE_ADMIN') {
      this.router.navigate([urlDestination]);
    } else {
      this.router.navigate([urlDestination]);
    }

  }

  // logout() {
  //   Cookie.delete('access_token');
  //   Cookie.delete('role');
  //   this.changeLoginStatus(false);
  // }

  changeLoginStatus(status: boolean): boolean {
    if (this.isLoggedIn !== undefined) {
      this.isLoggedIn.next(status);
    };
    return status;
  }

  token(): void {
    if (this.log) {
      console.log('token');
    }
    const options = new RequestOptions({ headers: this.headers, withCredentials: true });

    this.http.get(this.accountTokenUrl, options)
      .toPromise()
      .then(response => {
      })
      .catch((error: any) => observableThrowError(error.json().error || 'Server error'));
  }

  // BORRAME
  obtainAccessToken(loginData: Login) {
    const params = new URLSearchParams();
    params.append('username', loginData.usuarioEmail);
    params.append('password', loginData.usuarioContrasena);
    params.append('grant_type', 'password');
    params.append('client_id', 'healthapp');
    const headers = new Headers({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization':
        'Basic ' + btoa('healthapp:HeAltH@!23')
    });
    const options = new RequestOptions({ headers: headers });

    this.http.post(this.oauthTokenUrl, params.toString(), options).pipe(
      map(res => {
        if (this.log) {
          console.log('autenticado');
        }
        this.saveToken(res.headers);
        return new LoginStatus('SUCCESS', 'Login Successful')
      }))
      .subscribe(
      data => { if (this.log) { console.log(data); } },
      err => alert('Invalid Credentials'));
  }

  // saveToken(token) {
  //   const expireDate = new Date().getTime() + (1000 * token.expires_in);
  //   Cookie.set('access_token', token.access_token, expireDate);
  //   this.router.navigate(['/']);
  // }

  checkCredentials(): boolean {
    let result = false;
    if (this.log) {
      console.log('checkCredentials');
      console.log(Cookie.check('access_token'));
      console.log(Cookie.get('access_token'));
    }
    if (Cookie.check('access_token')) {
      result = this.changeLoginStatus(true);
    }
    return result;
  }

  logout() {
    Cookie.delete('access_token');
    Cookie.delete('role');
    this.changeLoginStatus(false);
    this.router.navigate(['/']);
  }

}
