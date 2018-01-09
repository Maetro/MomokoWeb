import { Component, OnInit, ViewChild, ComponentFactoryResolver } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { AnadirEntradaDirective } from '../anadir-entrada.directive';
import { EntradaPortada } from '../entrada-portada.model';
import { EntradaItem } from '../entrada-item';

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
