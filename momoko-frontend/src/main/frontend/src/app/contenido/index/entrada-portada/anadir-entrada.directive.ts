import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appAnadirEntrada]'
})
export class AnadirEntradaDirective {

  constructor(public viewContainerRef: ViewContainerRef) { }

}
