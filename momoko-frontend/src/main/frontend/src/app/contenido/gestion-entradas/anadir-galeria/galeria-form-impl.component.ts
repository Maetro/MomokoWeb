import { GaleriaForm } from './galeria-form.model';
import { Component, Input } from '@angular/core';


@Component({
  templateUrl: './galeria-form-impl.component.html'
})
export class GaleriaFormImplComponent implements GaleriaForm {
  @Input() data: any;

  selectedValue: number;

}
