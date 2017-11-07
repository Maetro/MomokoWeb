import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-nota-circular',
  templateUrl: './nota-circular.component.html',
  styleUrls: ['./nota-circular.component.css']
})
export class NotaCircularComponent implements OnInit {

  @Input() nota: number;

  constructor() { }

  ngOnInit() {
  }

}
