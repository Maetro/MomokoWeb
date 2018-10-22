import { Component, OnInit, OnChanges, SimpleChange } from '@angular/core';
import { environment } from 'environments/environment';
import { Libro } from '../../../../dtos/libro';
import { Genero } from '../../../../dtos/genre/genero';
import { SelectItem, Message, MessageService } from 'primeng/primeng';
import { FileUploadService } from '../../services/file-upload.service';
import { UtilService } from '../../../../services/util/util.service';
import { GeneralDataService } from '../../services/general-data.service';
import { LibroService } from '../../../../services/libro.service';

import { BookService } from '../book.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Editorial } from '../../../../dtos/editorial';
import { Author } from 'app/dtos/autor';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {
  private log = environment.log;

  libro: Libro;

  private bookUrl: string;

  listaGeneros: Genero[];

  generos: SelectItem[];

  selectedGeneros: string[] = [];

  changeLog: string[] = [];

  esLibroNuevo = false;

  nombresEditoriales: string[];

  nombresAutores: string[];

  numeroAutores = 1;

  customURL = false;

  urlImageServer = environment.urlFiles;

  constructor(
    private bookService: BookService,
    private libroService: LibroService,
    private generalDataService: GeneralDataService,
    private fileUploadService: FileUploadService,
    private util: UtilService,
    private route: ActivatedRoute,
    private router: Router,
    private messageService: MessageService
  ) {
    this.generos = [];
  }

  ngOnInit(): void {
    this.getGeneros();
    this.bookUrl = this.route.snapshot.paramMap.get('url');
    if (this.bookUrl) {
      this.bookService.getBookByUrl(this.bookUrl).subscribe(book => {
        this.libro = book;
        const generosLibro: Genero[] = book.generos;
        if (generosLibro != null) {
          generosLibro.forEach(generoLibro => {
            this.generos.forEach(genero => {
              if (generoLibro.generoId === genero.value) {
                this.selectedGeneros.push(genero.value);
              }
            });
          });
        }
        if (this.libro.filters){
          this.libro.filters.forEach(filter => {
            if (filter.inclusive){
              filter.stringSelectedValues = [];
              if (filter.selectedFilterValues){
                filter.selectedFilterValues.forEach(selectedValue => {
                  console.log(selectedValue);
                  console.log(filter.filterValues);
                  filter.filterValues.forEach(value => {
                    if(value.filterValueId === selectedValue){
                      filter.stringSelectedValues.push(value.value);
                    }
                  });
                });
                filter.selectedFilterValues = [];
              }
            
            }
          });
        }
      });
    } else {
      this.libro = new Libro();
      const emptyEditorial = new Editorial();
      this.libro.editorial = emptyEditorial;
      const autor = {} as Author;
      this.libro.autores = [];
      this.libro.autores.push(autor);
      this.libro.generos = [];
    }
    this.generalDataService.getInformacionGeneral().subscribe(
      datos => {
        if (this.log) {
          console.log('Init info general');
        }
        this.nombresAutores = datos.nombresAutores;
        this.nombresEditoriales = datos.nombresEditoriales;
      },
      error => {
        if (this.log) {
          console.log('Error al recuperar los datos generales ', error);
        }
      }
    );
  }

  getGeneros(): void {
    this.libroService.getGeneros().subscribe(generos => {
      this.listaGeneros = generos;
      generos.forEach(genero => {
        this.generos.push({
          label: ' ' + genero.nombre,
          value: genero.generoId
        });
      });
    });
  }

  onChange(event: any) {
    if (this.log) {
      console.log(event);
    }
    this.libro.generos = [];
    event.value.forEach(generoSeleccionadoId => {
      this.listaGeneros.forEach(genero => {
        if (genero.generoId === generoSeleccionadoId) {
          this.libro.generos.push(genero);
          const urlGenres = []
          this.libro.generos.forEach(genre => {
            urlGenres.push(genre.urlGenero);
          });
          this.bookService.getFiltersByGenres(urlGenres).subscribe(
            response => {
              this.libro.filters = response;
            }
          );
        }
      });
    });
  }

  guardarLibro(): void {
    if (this.libro.filters){
      this.libro.filters.forEach(filter => {
        if (filter.inclusive && filter.stringSelectedValues && filter.stringSelectedValues.length > 0){
          filter.stringSelectedValues.forEach(stringvalue => {
            filter.filterValues.forEach(possibleValue => {
              if (possibleValue.value === stringvalue){
                if (!filter.selectedFilterValues){
                  filter.selectedFilterValues = [];
                }
                filter.selectedFilterValues.push(possibleValue.filterValueId);
              }
            });
          });
        }
      });
    }
    this.libroService.guardarLibro(this.libro).subscribe(res => {
      if (res.estadoGuardado === 'CORRECTO') {
        this.showSuccess('Libro guardado correctamente');
        this.router.navigate(['/gestion/lista-libros']);
      } else {
        this.showError(res.listaErroresValidacion);
      }
    });
  }

  showSuccess(mensaje: string) {
    if (this.log) {
      console.log(mensaje);
    }
    this.messageService.add({ severity: 'success', summary: 'OK', detail: mensaje });
  }

  showError(mensaje: string[]) {
    if (this.log) {
      console.log(mensaje);
    }
    let mensajeTotal = '';
    mensaje.forEach(element => {
      mensajeTotal += element + '<br/>';
    });
    if (this.log) {
      console.log(mensajeTotal);
    }
    this.messageService.add({
      severity: 'error',
      summary: 'ERROR',
      detail: mensajeTotal
    });
  }

  fileChange($event): void {
    this.fileUploadService.fileChange($event, 'portadas').subscribe(
      urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        const partesURL = urlImagenNueva.split('/');
        const partes = partesURL[partesURL.length - 1].split('.');
        const urlImagen =
          this.urlImageServer +
          'portadas/' +
          this.util.convertToSlug(partes[0]) +
          '.' +
          partes[1];

        this.showSuccess('Imagen guardada correctamente');
        this.libro.urlImagen = urlImagen;
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      }
    );
  }

  urlChange(newValue: string) {
    this.customURL = true;
  }

  cambioTitulo(newValue: string) {
    if (!this.customURL) {
      this.libro.urlLibro = encodeURIComponent(
        this.util.convertToSlug(newValue)
      );
    }
  }

  volver(){
    this.router.navigate(['/gestion/lista-libros']);
  }

  crearAutor() {
    const autor = {} as Author;
    this.libro.autores.push(autor);
  }
}
