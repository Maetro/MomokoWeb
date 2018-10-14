import { Component, Input, OnInit } from '@angular/core';
import { BookTemplateItem } from './book-template-item';
import { BookTemplateBookComponent } from './book-template-book.component';

declare var $: any;

@Component({
  template: `
  <div class="row" bloqueLibro>
  <div class="col-xs-12"> 
      <div class="bookTemplateContainer">
      <a routerLink="/libro/{{data.url}}">
          <div class="imageBookTemplateContainer"> 
              <app-libro3d [anchura]="getAvaliableWidth()" [anchuraPortada]="data.anchura" [alturaPortada]="data.altura" [portada]="data.cover"></app-libro3d>
          </div> 
          <div class="textBookTemplateContainer"> 
            <p> {{data.title}} - {{data.authorName}} </p> 
        </div> 
      </a>
  </div> 
</div> 
</div>
  `
})
export class BookTemplateInstanceComponent
  implements BookTemplateBookComponent, OnInit {
  @Input()
  data: any;

  avaliableWidth: string;

  ngOnInit() {
    this.avaliableWidth = $('.tituloEntrada').css('width');
    console.log('On Init BookTemplateInstanceComponent');
  }

  getAvaliableWidth() {
    console.log('getAvaliableWidth');
    return (
      parseFloat(this.avaliableWidth.substring(-2)) / this.data.columnas - 20
    );
  }
}
