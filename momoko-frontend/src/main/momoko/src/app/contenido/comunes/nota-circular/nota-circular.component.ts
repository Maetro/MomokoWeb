import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-nota-circular',
  templateUrl: './nota-circular.component.html',
  styleUrls: ['./nota-circular.component.css']
})
export class NotaCircularComponent implements OnInit {

  private log = environment.log;

  @Input() nota: number;

  constructor() { }

  ngOnInit() {
  }

}

