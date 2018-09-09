import { Component, OnInit, ViewChild } from '@angular/core';
import { environment } from 'environments/environment';
import { Libro } from '../../../../dtos/libro';
import { Genero } from '../../../../dtos/genre/genero';
import { BookService } from '../book.service';
import { FileUploadService } from '../../services/file-upload.service';
import { Router } from '@angular/router';
import { Table } from 'primeng/table';
import { Globals } from '../../../../app.globals';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  
  private log = environment.log;

  selectedBook: Libro;

  @ViewChild(Table) table: Table;

  cols: any[];

  loading: boolean;

  title = 'Libros';
  books: Libro[];

  filter: string;

  constructor(private bookService: BookService, private router: Router, private globals: Globals) {
    if (this.log) {
      console.log('Builder BookListComponent');
    }
    this.books = [];
    this.filter = globals.bookfilter;
  }

  getBooks(): void {
    if (this.log) {
      console.log('service -> getBooks()');
    }
    this.bookService.getBooks().subscribe(books => {
      books.forEach(book => {
        this.books = [...this.books, book];
      });
    });
  }

  ngOnInit(): void {
    if (this.log) {
      console.log('ngOnInit Lista getBooks');
    }
    this.loading = true;
    this.bookService.getBooks().subscribe(booksP => {
      const booksList = booksP;
      booksList.forEach(book => {
        this.books = [...this.books, book];
      });
      this.table.filterGlobal(this.globals.bookfilter, 'contains');
      this.loading = false;
    });
    this.cols = [
      { field: 'titulo', header: 'Título' },
      { field: 'editorial.nombreEditorial', header: 'Nombre editorial' },
      { field: 'autoresString', header: 'Autores' },
      { field: 'generosString', header: 'Generos' },
      { field: 'anoEdicion', header: 'Año edición' },
      { field: 'fechaAlta', header: 'Fecha alta' }
    ];
    

  }

  newBook(): void {
    if (this.log) {
      console.log('nuevoBook');
    }
    this.router.navigate(['/gestion/nuevo-libro']);
  }

  editBook(book: any) {
    this.router.navigate(['/gestion/editar-libro/' + book.urlLibro]);
  }

  volver(): void {
    if (this.log) {
      console.log('volver');
    }
  }

  actualizarOAnadirBook(book: Libro): void {
    if (this.log) {
      console.log('actualizarOAnadirBook ' + book);
    }
    let encontrado = false;
    this.books.forEach(red => {
      if (red.libroId === book.libroId) {
        red = book;
        encontrado = true;
      }
    });
    if (!encontrado) {
      this.books = [...this.books, book];
    }
  }

  onRowSelect(event) {
    if (this.log) {
      console.log('onRowSelect');
    }
  }

  onChangeFilter($event){
    console.log($event.target.value);
    this.globals.bookfilter = $event.target.value;
  }

}
