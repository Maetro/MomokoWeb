import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-nota-circular',
  templateUrl: './nota-circular.component.html',
  styleUrls: ['./nota-circular.component.css']
})
export class NotaCircularComponent implements OnInit {

  private log = environment.log;

  notaRedondeada: number

  @Input() nota: number;

  constructor() { }

  ngOnInit() {
    this.notaRedondeada = Math.round( this.nota) / 10;

  }

}

