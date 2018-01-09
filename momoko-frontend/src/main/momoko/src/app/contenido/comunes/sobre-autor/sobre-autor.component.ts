import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { User } from '../../../dtos/user';

@Component({
  selector: 'app-sobre-autor',
  templateUrl: './sobre-autor.component.html',
  styleUrls: ['./sobre-autor.component.css']
})
export class SobreAutorComponent implements OnInit {

  private log = environment.log;

  @Input() autor: User;

  constructor() { }

  ngOnInit() {
  }

}
