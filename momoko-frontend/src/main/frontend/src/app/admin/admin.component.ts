import { Component, OnInit } from '@angular/core';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  private log = environment.log;

  constructor() { }

  ngOnInit() {
    if (this.log) {
      console.log('Iniciando Admin Component');
    }

  }

}
