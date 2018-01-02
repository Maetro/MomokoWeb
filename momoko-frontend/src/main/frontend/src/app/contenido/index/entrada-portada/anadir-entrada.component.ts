import { Component, AfterViewInit, OnDestroy, Input, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { EntradaItem } from 'app/contenido/index/entrada-portada/entrada-item';
import { AnadirEntradaDirective } from 'app/contenido/index/entrada-portada/anadir-entrada.directive';
import { EntradaPortada } from 'app/contenido/index/entrada-portada/entrada-portada.model';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-anadir-entrada',
  template: '<ng-template appAnadirEntrada></ng-template>',
})

export class AnadirEntradaComponent {

  private log = environment.log;

  @ViewChild(AnadirEntradaDirective) anadirEntradaHost: AnadirEntradaDirective;

  constructor(private componentFactoryResolver: ComponentFactoryResolver) { }

  loadComponent(entradaItem: EntradaItem) {

    if (this.log) {
      console.log('Cargando entrada');
    }

    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(entradaItem.component);

    const viewContainerRef = this.anadirEntradaHost.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent(componentFactory);
    (<EntradaPortada>componentRef.instance).data = entradaItem.entrada;

  }

}
