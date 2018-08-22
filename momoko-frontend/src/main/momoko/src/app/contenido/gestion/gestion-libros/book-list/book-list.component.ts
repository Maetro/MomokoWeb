import { Component, OnInit } from '@angular/core';
import { environment } from 'environments/environment';
import { Libro } from '../../../../dtos/libro';
import { Genero } from '../../../../dtos/genre/genero';
import { BookService } from '../book.service';
import { FileUploadService } from '../../services/file-upload.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  
  private log = environment.log;

  selectedBook: Libro;

  cols: any[];

  loading: boolean;

  title = 'Libros';
  books: Libro[];

  constructor(private bookService: BookService, private router: Router) {
    if (this.log) {
      console.log('Builder BookListComponent');
    }
    this.books = [];
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
      this.loading = false;
    });
    this.cols = [
      { field: 'titulo', header: 'Título' },
      { field: 'editorial.nombreEditorial', header: 'Nombre editorial' },
      { field: 'autoresString', header: 'Autores' },
      { field: 'generosString', header: 'Generos' },
      { field: 'anoEdicion', header: 'Año edición' }
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

}
