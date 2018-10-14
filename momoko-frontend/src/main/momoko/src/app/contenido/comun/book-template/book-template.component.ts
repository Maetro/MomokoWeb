import { Component, OnInit, OnDestroy, Input, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { BookTemplateItem } from './book-template-item';
import { BookTemplateDirective } from './book-template.directive';
import { BookTemplateInstanceComponent } from './book-template-instance.component';
import { BookTemplateBookComponent } from './book-template-book.component';

@Component({
  selector: 'app-book-template',
  templateUrl: './book-template.component.html',
  styleUrls: ['./book-template.component.scss']
})
export class BookTemplateComponent implements OnInit {

  @Input() bookTemp: string;
  currentAdIndex = -1;
  @ViewChild(BookTemplateDirective) bookTemplateImpl: BookTemplateDirective;
  interval: any;

  constructor(private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit() {
    var columnas = this.bookTemp.substring(
      this.bookTemp.lastIndexOf('columnas="') + 10,
      this.bookTemp.indexOf('\"', this.bookTemp.lastIndexOf('columnas="') + 10));
    var url = this.bookTemp.substring(
        this.bookTemp.lastIndexOf('url="') + 5,
        this.bookTemp.indexOf('\"', this.bookTemp.lastIndexOf('url="') + 5));
    var anchura = this.bookTemp.substring(
      this.bookTemp.lastIndexOf('anchura="') + 9,
      this.bookTemp.indexOf('\"', this.bookTemp.lastIndexOf('anchura="') + 9));
    var altura = this.bookTemp.substring(
      this.bookTemp.lastIndexOf('altura="') + 8,
      this.bookTemp.indexOf('\"', this.bookTemp.lastIndexOf('altura="') + 8));
    var imagen = this.bookTemp.substring(
        this.bookTemp.lastIndexOf('imagen="') + 8,
        this.bookTemp.indexOf('\"', this.bookTemp.lastIndexOf('imagen="') + 8));
    var title = this.bookTemp.substring(
          this.bookTemp.lastIndexOf('title="') + 7,
          this.bookTemp.indexOf('\"', this.bookTemp.lastIndexOf('title="') + 7));
    var author = this.bookTemp.substring(
      this.bookTemp.lastIndexOf('autor="') + 7,
      this.bookTemp.indexOf('\"', this.bookTemp.lastIndexOf('autor="') + 7));
    let bookTemplate = new BookTemplateItem(BookTemplateInstanceComponent, {title: title, cover: imagen, authorName: author,
    anchura: anchura, altura: altura, columnas: columnas, url:url})
    let componentFactory = this.componentFactoryResolver.resolveComponentFactory(bookTemplate.component);
    let viewContainerRef = this.bookTemplateImpl.viewContainerRef;
    viewContainerRef.clear();

    let componentRef = viewContainerRef.createComponent(componentFactory);
    (<BookTemplateBookComponent>componentRef.instance).data = bookTemplate.data;
  }


}
