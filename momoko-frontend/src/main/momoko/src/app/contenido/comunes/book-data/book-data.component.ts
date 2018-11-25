import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Libro } from 'app/dtos/libro';

@Component({
  selector: 'app-book-data',
  templateUrl: './book-data.component.html',
  styleUrls: ['./book-data.component.scss']
})
export class BookDataComponent implements OnInit {

  private log = environment.log;

  @Input() book: Libro;

  constructor() { }

  ngOnInit() {

  }

}