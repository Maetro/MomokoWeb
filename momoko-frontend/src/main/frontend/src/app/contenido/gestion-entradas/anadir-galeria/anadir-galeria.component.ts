import { GaleriaForm } from './galeria-form.model';
import { AnadirGaleriaDirective } from './anadir-galeria.directive';
import { GaleriaItem } from './galeria-item';
import { Component, AfterViewInit, OnDestroy, Input, ViewChild, ComponentFactoryResolver } from '@angular/core';

@Component({
  selector: 'app-anadir-galeria',
  templateUrl: './anadir-galeria.component.html'
})

export class AnadirGaleriaComponent implements AfterViewInit, OnDestroy  {

  @Input() galerias: GaleriaItem[];

  @ViewChild(AnadirGaleriaDirective) anadirGaleriaHost: AnadirGaleriaDirective;

  constructor(private componentFactoryResolver: ComponentFactoryResolver) {}

  ngAfterViewInit() {
    this.loadComponent();
    this.getAds();
  }

  ngOnDestroy() {
  }

  loadComponent() {

    const galeriaItem = this.galerias[0];

    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(galeriaItem.component);

    const viewContainerRef = this.anadirGaleriaHost.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent(componentFactory);
    (<GaleriaForm>componentRef.instance).data = galeriaItem.data;
  }

  getAds() {
  }
}
