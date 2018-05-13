import { Component, OnInit, Input } from '@angular/core';
import { InfoAdicional } from 'app/dtos/infoAdicional';

@Component({
  selector: 'app-info-adicional',
  templateUrl: './info-adicional.component.html',
  styleUrls: ['./info-adicional.component.css']
})
export class InfoAdicionalComponent implements OnInit {
  @Input() infoAdicional: InfoAdicional[];

  constructor() {}

  ngOnInit() {}

  anadirInfo() {
    console.log('Add');
    if (!this.infoAdicional) {
      this.infoAdicional = new Array();
    }
    const info = new InfoAdicional();
    this.infoAdicional.push(info);
  }
}
