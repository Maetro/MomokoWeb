import { Component, OnInit, ViewChild } from '@angular/core';
import { Galeria } from '../../../dtos/galeria';
import { GaleriaService } from '../../../services/galeria.service';
import { FileUploadService } from '../../../services/fileUpload.service';
import { GaleriaDetailComponent } from '../galeria-detail/galeria-detail.component';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-lista-galerias',
  templateUrl: './lista-galerias.component.html',
  styleUrls: ['./lista-galerias.component.css']
})
export class ListaGaleriasComponent implements OnInit {

  private log = environment.log;

  loading: boolean;

  title = 'Galerías';
  galerias: Galeria[];
  selectedGaleria: Galeria;

  @ViewChild(GaleriaDetailComponent) galeriaDetailComponent: GaleriaDetailComponent;

  constructor(private galeriaService: GaleriaService, private fileUploadService: FileUploadService) {
    if (this.log) {
      console.log('Builder ListaGaleriasComponent');
    }
    this.galerias = [];

  }

  getGalerias(): void {
    if (this.log) {
      console.log('service -> getGalerias()');
    }
    this.galeriaService.getGalerias().subscribe(galerias => {
      galerias.forEach(galeria => {
        this.galerias = [...this.galerias, galeria];
      });

    });
  }

  ngOnInit(): void {
    if (this.log) {
      console.log('ngOnInit Lista galerias');
    }
    this.loading = true;
    this.galeriaService.getGalerias().subscribe(galeriasP => {
      const galeriasList = galeriasP
      galeriasList.forEach(element => {
        this.galerias = [...this.galerias, element];
      });
      this.loading = false;
    });
  }

  selectGaleria(galeria: Galeria) {
    if (this.log) {
      console.log('select Galeria');
    }
    this.selectedGaleria = galeria;
  }

  nuevoGaleria(): void {
    if (this.log) {
      console.log('nueva Galeria');
    }
    this.selectedGaleria = null;
    const galeria = new Galeria;
    galeria.imagenes = [];
    galeria.columnas = 3;
    this.galeriaDetailComponent.numeroFilas = 1;
    this.selectedGaleria = galeria;
  }

  volver(): void {
    if (this.log) {
      console.log('volver');
    }
    this.selectedGaleria = null;
  }

  actualizarOAnadirGaleria(galeria: Galeria): void {
    if (this.log) {
      console.log('actualizarOAnadirGaleria ' + galeria);
    }
    this.selectedGaleria = null;
    let encontrado = false;
    this.galerias.forEach(gen => {
      if (gen.urlGaleria === galeria.urlGaleria) {
        gen = galeria;
        encontrado = true;
      }
    });
    if (!encontrado) {
      this.galerias = [...this.galerias, galeria];
    }
  }

  onRowSelect(event) {
    if (this.log) {
      console.log('onRowSelect');
    }
  }

}
