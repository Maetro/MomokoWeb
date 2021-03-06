
import { Component, OnInit, Input } from '@angular/core';
import { environment } from 'environments/environment';
import { Redactor } from 'app/dtos/redactor';


@Component({
  selector: 'app-redactor-info',
  templateUrl: './redactor-info.component.html',
  styleUrls: ['./redactor-info.component.css']
})
export class RedactorInfoComponent implements OnInit {

  private log = environment.log;
  
  @Input() redactor: Redactor;

  constructor() { }

  ngOnInit() {
  }

}
