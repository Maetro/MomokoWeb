import { EntradaUrl } from '../../../dtos/entradaurl';
import { HerramientasService } from '../../../services/herramientas.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { environment } from 'environments/environment';
import { Redactor } from '../../../dtos/redactor';
import { RedactorDetailComponent } from '../redactor-detail/redactor-detail.component';
import { FileUploadService } from '../../../services/fileUpload.service';
import { RedactorService } from '../../../services/redactor.service';

@Component({
  selector: 'app-lista-redactores',
  templateUrl: './lista-redactores.component.html',
  styleUrls: ['./lista-redactores.component.css']
})
export class ListaRedactoresComponent implements OnInit {
  private log = environment.log;

  loading: boolean;

  title = 'Libros';
  redactores: Redactor[];
  selectedRedactor: Redactor;

  @ViewChild(RedactorDetailComponent)
  redactorDetailComponent: RedactorDetailComponent;

  constructor(
    private redactorService: RedactorService,
    private fileUploadService: FileUploadService
  ) {
    if (this.log) {
      console.log('Builder ListaRedactoresComponent');
    }
    this.redactores = [];
  }

  getGeneros(): void {
    if (this.log) {
      console.log('service -> getRedactores()');
    }
    this.redactorService.getRedactores().subscribe(redactores => {
      redactores.forEach(redactor => {
        this.redactores = [...this.redactores, redactor];
      });
    });
  }

  ngOnInit(): void {
    if (this.log) {
      console.log('ngOnInit Lista getRedactores');
    }
    this.loading = true;
    this.redactorService.getRedactores().subscribe(generosP => {
      const redactoresList = generosP;
      redactoresList.forEach(redactor => {
        this.redactores = [...this.redactores, redactor];
      });
      this.loading = false;
    });
  }

  selectRedactor(redactor: Redactor) {
    if (this.log) {
      console.log('selectLibro');
    }
    this.selectedRedactor = redactor;
  }

  nuevoRedactor(): void {
    if (this.log) {
      console.log('nuevoRedactor');
    }
    this.selectedRedactor = null;
    const redactor = new Redactor();
    this.selectedRedactor = redactor;
  }

  volver(): void {
    if (this.log) {
      console.log('volver');
    }
    this.selectedRedactor = null;
  }

  actualizarOAnadirRedactor(redactor: Redactor): void {
    if (this.log) {
      console.log('actualizarOAnadirRedactor ' + redactor);
    }
    this.selectedRedactor = null;
    let encontrado = false;
    this.redactores.forEach(red => {
      if (red.usuarioId === redactor.usuarioId) {
        red = redactor;
        encontrado = true;
      }
    });
    if (!encontrado) {
      this.redactores = [...this.redactores, redactor];
    }
  }

  onRowSelect(event) {
    if (this.log) {
      console.log('onRowSelect');
    }
  }
}
