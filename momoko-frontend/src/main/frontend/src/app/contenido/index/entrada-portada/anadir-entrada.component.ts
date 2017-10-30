import { Component, AfterViewInit, OnDestroy, Input, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { EntradaItem } from 'app/contenido/index/entrada-portada/entrada-item';
import { AnadirEntradaDirective } from 'app/contenido/index/entrada-portada/anadir-entrada.directive';
import { EntradaPortada } from 'app/contenido/index/entrada-portada/entrada-portada.model';

@Component({
  selector: 'app-anadir-entrada',
  template: '<ng-template appAnadirEntrada></ng-template>',
})

export class AnadirEntradaComponent {

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
