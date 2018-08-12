import { Component, AfterViewInit, OnDestroy, Input, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { EntradaItem } from './entrada-item';
import { AnadirEntradaDirective } from './anadir-entrada.directive';
import { EntradaPortada } from './entrada-portada.model';

@Component({
  selector: 'app-anadir-entrada2',
  template: '<ng-template appAnadirEntrada></ng-template>',
})

export class AnadirEntrada2Component {

  @ViewChild(AnadirEntradaDirective) anadirEntradaHost: AnadirEntradaDirective;

  constructor(private componentFactoryResolver: ComponentFactoryResolver) { }

  loadComponent(entradaItem: EntradaItem) {

    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(entradaItem.component);

    const viewContainerRef = this.anadirEntradaHost.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent(componentFactory);
    (<EntradaPortada>componentRef.instance).data = entradaItem.entrada;

  }

}
