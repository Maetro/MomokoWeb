import { Component, OnInit } from '@angular/core';

@Component({
  template:  `
    <router-outlet></router-outlet>
  `
})
export class AuthComponent implements OnInit{

  ngOnInit(): void {
    console.log('ngOnInit AuthComponent')
  }

}
