

import { Injectable } from '@angular/core';
import { Http, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';


import { Observer } from 'rxjs/Observer';
import { Headers } from '@angular/http';
import { Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { environment } from '../../../../environments/environment';
import { SignupStatus, NewUser, LoginStatus, Login } from '../dtos/login';

@Injectable()
export class AuthService {

  log = environment.log;

  isLoggedIn: Observable<boolean>;
  private observer: Observer<boolean>;

  user = { name: 'Guest' };
  redirectUrl: string;
  singUpUrl = environment.singUpURL;
  oauthTokenUrl = environment.oauthTokenUrl;
  accountTokenUrl = environment.accountTokenUrl;
  headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http, private router: Router) {
    this.isLoggedIn = new Observable(observer =>
      this.observer = observer
    );
  }

  checkLoginStatus(): Observable<boolean> {
    return this.isLoggedIn;
  }

  signup(newUser: NewUser): Promise<SignupStatus> {
    const options = new RequestOptions({ headers: this.headers });

    return this.http.post(this.singUpUrl, newUser, options)
      .toPromise()
      .then(res => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  login(login: Login): Observable<LoginStatus> {
    const params = new URLSearchParams();
    if (this.log) {
      console.log('Login');
    }
    params.append('username', login.usuarioEmail);
    params.append('password', login.usuarioContrasena);
    params.append('grant_type', 'password');
    params.append('client_id', 'healthapp');
    const headers = new Headers({
      'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Basic ' +
        btoa('healthapp:HeAltH@!23')
    });
    const options = new RequestOptions({ headers: headers });

    return this.http.post(this.oauthTokenUrl, params.toString(), options)
      .map(res => {
        this.saveToken(res.json());
        return new LoginStatus('SUCCESS', 'Login Successful');
      })
      .catch((error: any) => {
        return Observable.of(new LoginStatus('FAILURE', 'Username or password is incorrect. Please try again!'));
      });
  }

  saveToken(token: any) {
    if (this.log) {
      console.log('Obteniendo token de autenticacion');
      console.log(token.expires_in);
    }
    const expireDate = new Date().getTime() + (24000 * token.expires_in);
    Cookie.set('access_token', token.access_token, expireDate);
    Cookie.set('role', token.role);
    this.changeLoginStatus(true);
    if (token.role === 'ROLE_ADMIN') {
      this.router.navigate(['/']);
    } else {
      this.router.navigate(['/']);
    }

  }

  // logout() {
  //   Cookie.delete('access_token');
  //   Cookie.delete('role');
  //   this.changeLoginStatus(false);
  // }

  changeLoginStatus(status: boolean) {
    if (this.observer !== undefined) {
      this.observer.next(status);
    };
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
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
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

    this.http.post(this.oauthTokenUrl, params.toString(), options)
      .map(res => {
        if (this.log) {
          console.log('autenticado');
        }
        this.saveToken(res.json());
        return new LoginStatus('SUCCESS', 'Login Successful')
      })
      .subscribe(
      data => { if (this.log) { console.log(data); } },
      err => alert('Invalid Credentials'));
  }

  // saveToken(token) {
  //   const expireDate = new Date().getTime() + (1000 * token.expires_in);
  //   Cookie.set('access_token', token.access_token, expireDate);
  //   this.router.navigate(['/']);
  // }

  checkCredentials() {
    if (this.log) {
      console.log('checkCredentials');
    }
    if (!Cookie.check('access_token')) {
      this.router.navigate(['/auth/login']);
    } else {
      this.isLoggedIn = new Observable(observer =>
        this.observer = observer
      );
      this.changeLoginStatus(true);
    }
  }

  logout() {
    Cookie.delete('access_token');
    this.router.navigate(['/auth/login']);
  }

}
