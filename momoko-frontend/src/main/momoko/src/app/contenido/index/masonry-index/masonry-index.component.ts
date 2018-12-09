import { Component, Input, AfterViewInit} from '@angular/core';
import { EntradaSimple } from 'app/dtos/entradaSimple';

@Component({
  selector: 'app-masonry-index',
  templateUrl: './masonry-index.component.html',
  styleUrls: ['./masonry-index.component.scss']
})
export class MasonryIndexComponent{

  updateMasonry = false;

  @Input() items: EntradaSimple[];

  constructor() { }

  ngOnInit() {
  }


}
