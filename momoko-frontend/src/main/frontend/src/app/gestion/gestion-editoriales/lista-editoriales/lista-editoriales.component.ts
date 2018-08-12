import { Component, OnInit, ViewChild } from '@angular/core';
import { environment } from 'environments/environment';
import { Editorial } from '../../../dtos/editorial';
import { EditorialDetailComponent } from '../editorial-detail/editorial-detail.component';
import { FileUploadService } from '../../../services/fileUpload.service';
import { EditorialService } from '../../../services/editorial.service';

@Component({
  selector: 'app-lista-editoriales',
  templateUrl: './lista-editoriales.component.html',
  styleUrls: ['./lista-editoriales.component.css']
})
export class ListaEditorialesComponent implements OnInit {
  private log = environment.log;

  loading: boolean;

  title = 'Libros';
  editoriales: Editorial[];
  selectedEditorial: Editorial;

  @ViewChild(EditorialDetailComponent)
  editorialDetailComponent: EditorialDetailComponent;

  constructor(
    private editorialService: EditorialService,
    private fileUploadService: FileUploadService
  ) {
    if (this.log) {
      console.log('Builder ListaEditorialesComponent');
    }
    this.editoriales = [];
  }

  getGeneros(): void {
    if (this.log) {
      console.log('service -> getEditoriales()');
    }
    this.editorialService.getEditoriales().subscribe(editoriales => {
      editoriales.forEach(editorial => {
        this.editoriales = [...this.editoriales, editorial];
      });
    });
  }

  ngOnInit(): void {
    if (this.log) {
      console.log('ngOnInit Lista getEditoriales');
    }
    this.loading = true;
    this.editorialService.getEditoriales().subscribe(generosP => {
      const editorialesList = generosP;
      editorialesList.forEach(editorial => {
        this.editoriales = [...this.editoriales, editorial];
      });
      this.loading = false;
    });
  }

  selectEditorial(editorial: Editorial) {
    if (this.log) {
      console.log('selectLibro');
    }
    if (!editorial.infoAdicional) {
      editorial.infoAdicional = new Array();
    }
    this.selectedEditorial = editorial;
  }

  nuevaEditorial(): void {
    if (this.log) {
      console.log('nuevoEditorial');
    }
    this.selectedEditorial = null;
    const editorial = new Editorial();
    editorial.infoAdicional = new Array();
    this.selectedEditorial = editorial;
  }

  volver(): void {
    if (this.log) {
      console.log('volver');
    }
    this.selectedEditorial = null;
  }

  actualizarOAnadirEditorial(editorial: Editorial): void {
    if (this.log) {
      console.log('actualizarOAnadirEditorial ' + editorial);
    }
    this.selectedEditorial = null;
    let encontrado = false;
    this.editoriales.forEach(ed => {
      if (ed.nombreEditorial === editorial.nombreEditorial) {
        ed = editorial;
        encontrado = true;
      }
    });
    if (!encontrado) {
      this.editoriales = [...this.editoriales, editorial];
    }
  }

  onRowSelect(event) {
    if (this.log) {
      console.log('onRowSelect');
    }
    if (!event.data.infoAdicional) {
      event.data.infoAdicional = new Array();
    }
  }
}
