import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[custom-block-index]',
})
export class CustomBlockIndexDirective {
  constructor(public viewContainerRef: ViewContainerRef) { }
}