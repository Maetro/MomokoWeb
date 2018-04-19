import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-redactor-detail',
  templateUrl: './redactor-detail.component.html',
  styleUrls: ['./redactor-detail.component.css']
})
export class RedactorDetailComponent implements OnInit {

  idRedactorSeleccionado: number;

  constructor() { }

  ngOnInit() {
  }

}
