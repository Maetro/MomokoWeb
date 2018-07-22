import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-nota-circular-pequena',
  templateUrl: './nota-circular-pequena.component.html',
  styleUrls: ['./nota-circular-pequena.component.css']
})
export class NotaCircularPequenaComponent implements OnInit {

  private log = environment.log;

  notaRedondeada: number

  @Input() nota: number;

  constructor() { }

  ngOnInit() {
    this.notaRedondeada = Math.round( this.nota) / 10;

  }

}

