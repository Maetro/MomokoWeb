import { Component, Input } from '@angular/core';
import { Libro } from './libro';

@Component({
  selector: 'libro-detail',
  templateUrl: './editor-libro.component.html'
})
export class LibroDetailComponent {
  @Input() libro: Libro;
}
