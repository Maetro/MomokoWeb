import { Component, OnInit, Input } from '@angular/core';
import { Columna } from '../../columna';

@Component({
  selector: 'app-quill-editor',
  templateUrl: './quill-editor.component.html',
  styleUrls: ['./quill-editor.component.css']
})
export class QuillEditorComponent implements OnInit {

  @Input() numeroFila: number;

  @Input() numeroColumna: number;

  @Input() columna: Columna;

  constructor() { }

  ngOnInit() {
  }

}
