import { GaleriaForm } from './galeria-form.model';
import { Component, Input } from '@angular/core';
import { FileUploadService } from 'app/services/fileUpload.service';


@Component({
  templateUrl: './galeria-form-impl.component.html'
})
export class GaleriaFormImplComponent implements GaleriaForm {
  @Input() data: any;

  selectedValue: number;

  constructor(private fileUploadService: FileUploadService) { };

  fileChange($event): void {
    this.fileUploadService.fileChange($event, 'imagenes-destacadas').subscribe
      (urlImagenNueva => {
        // Emit list event

      },
      err => {
        // Log errors if any
        console.log(err);
      });
  }

}
