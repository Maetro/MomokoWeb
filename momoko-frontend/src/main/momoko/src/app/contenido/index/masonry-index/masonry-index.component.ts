import { Component, Input, AfterViewInit} from '@angular/core';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { NgxMasonryOptions } from 'ngx-masonry';

@Component({
  selector: 'app-masonry-index',
  templateUrl: './masonry-index.component.html',
  styleUrls: ['./masonry-index.component.scss']
})
export class MasonryIndexComponent{

  updateMasonry = false;

  myOptions: NgxMasonryOptions = {
    columnWidth: 370,
    gutter: 20
  }

  @Input() items: EntradaSimple[];

  constructor() { }

  ngOnInit() {
  }


}
