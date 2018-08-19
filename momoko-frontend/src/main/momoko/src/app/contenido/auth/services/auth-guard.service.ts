import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateChild, Route, CanLoad } from '@angular/router';
import { AuthService } from './auth.service';
import { environment } from '../../../../environments/environment';




@Injectable()
export class AuthGuardService implements CanActivate, CanActivateChild, CanLoad {

  private log = environment.log;

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.log) {
      console.log('canActivate: ');
    }
    const url: string = state.url;

    return this.checkLogin(url);
  }

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.log) {
      console.log('canActivateChild: ');
    }
    return this.canActivate(route, state);
  }

  canLoad(route: Route): boolean {
    if (this.log) {
      console.log('canLoad: ');
    }
    let url = `/${route.path}`;

    return this.checkLogin(url);
  }

  checkLogin(url: string): boolean {
    let result = false;
    this.authService.isLoggedIn.subscribe(isAuthenticated => {
        if (this.log) {
          console.log('checkLogin: '+ url + ' isLoggedIn: ' + isAuthenticated)
        }
        if (isAuthenticated) { result = true; } else {
        // Store the attempted URL for redirecting
        this.authService.redirectUrl = url;
    
        // Navigate to the login page with extras
        this.router.navigate(['/auth/login']);
        }

    });
    
    return result;
  }
}
