import { Component, OnInit } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { Login } from '../../dtos/login';
import { AuthService } from '../../services/auth.service';
import { HttpClient, HttpHeaders } from '../../../../../../node_modules/@angular/common/http';
import { Router, ActivatedRoute } from '../../../../../../node_modules/@angular/router';
import { Cookie } from '../../../../../../node_modules/ng2-cookies';


@Component({
  templateUrl: './social-login-success.component.html'
})
export class SocialLoginSuccessComponent implements OnInit{

    private log = environment.log;

    private sub: any;

    private token: string;

    constructor(private authService: AuthService, private http: HttpClient, private router: Router,
        private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.queryParams.subscribe(params => {
            console.log(params['token']);
            if (this.log) {
                console.log('Authorization: ' + params['token']);
              }
              const headers = new HttpHeaders({
                'Content-type': 'application/json',
                'Authorization': 'Bearer ' + params['token']
              });
              this.http.get('http://localhost:5000/api/core/context', { headers: headers }).subscribe(response => {
                  console.log(response);
              })
        });
    }

}