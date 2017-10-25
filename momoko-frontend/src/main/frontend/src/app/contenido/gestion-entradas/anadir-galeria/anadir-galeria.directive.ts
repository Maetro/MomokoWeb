import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appAnadirGaleria]'
})
export class AnadirGaleriaDirective {

  constructor(public viewContainerRef: ViewContainerRef) { }

}
