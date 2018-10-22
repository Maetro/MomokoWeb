import { Component, OnInit } from '@angular/core';
import { environment } from 'environments/environment';
import { Author } from 'app/dtos/autor';
import { AuthorService } from '../service/author.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.scss']
})
export class AuthorListComponent implements OnInit {
  private log = environment.log;

  selectedAuthor: Author;

  cols: any[];

  loading: boolean;

  title = 'Libros';
  authors: Author[];

  constructor(private authorService: AuthorService, private router: Router) {
    if (this.log) {
      console.log('Builder ListaFilteresComponent');
    }
    this.authors = [];
  }

  getAuthors(): void {
    if (this.log) {
      console.log('service -> getFilteres()');
    }
    this.authorService.getAuthors().subscribe(authors => {
      authors.forEach(author => {
        this.authors = [...this.authors, author];
      });
    });
  }

  ngOnInit(): void {
    if (this.log) {
      console.log('ngOnInit Lista getFilteres');
    }
    this.loading = true;
    this.authorService.getAuthors().subscribe(authors => {
      authors.forEach(author => {
        this.authors = [...this.authors, author];
      });
      this.loading = false;
    });
    this.cols = [
      { field: 'name', header: 'Nombre' },
      { field: 'authorUrl', header: 'Url' },
      { filed: 'isCompleted', header: 'Información completa'}
    ];
  }

  newAuthor(): void {
    if (this.log) {
      console.log('nuevoFilter');
    }
    this.router.navigate(['/gestion/nuevo-autor']);
  }

  editAuthor(author: Author) {
    this.router.navigate(['/gestion/editar-autor/' + author.authorUrl]);
  }

  volver(): void {
    if (this.log) {
      console.log('volver');
    }
  }

  actualizarOAnadirFilter(author: Author): void {
    if (this.log) {
      console.log('actualizarOAnadirAuthor ' + author);
    }
    let encontrado = false;
    this.authors.forEach(aut => {
      if (aut.authorId === author.authorId) {
        aut = author;
        encontrado = true;
      }
    });
    if (!encontrado) {
      this.authors = [...this.authors, author];
    }
  }

  onRowSelect(event) {
    if (this.log) {
      console.log('onRowSelect');
    }
  }

  isCompleted(author: Author){
    let isCompleted = 'Incompleto';
    if (author.description && author.description.length > 0){
      isCompleted = 'Completado';
    }
    return isCompleted;
  }

}
