import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appBookTemplate]'
})
export class BookTemplateDirective {

  constructor(public viewContainerRef: ViewContainerRef) { }

}
