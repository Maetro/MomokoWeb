import { GaleriaForm } from './galeria-form.model';
import { Component, Input } from '@angular/core';


@Component({
  template: `
    <div class="hero-profile">
      <h3>Featured Hero Profile</h3>
      <h4>{{data.name}}</h4>

      <strong>Hire this hero today!</strong>
    </div>
  `
})
export class GaleriaFormImplComponent implements GaleriaForm {
  @Input() data: any;
}
