import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
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

  @Output() editorData: EventEmitter<string> = new EventEmitter<string>();

  constructor() { }

  ngOnInit() {
  }

  addEditorData(event: any){
    console.log('#editor-'+event.numeroFila + '-' + event.numeroColumna);
    this.editorData.emit('#editor-'+event.numeroFila + '-' + event.numeroColumna);
  }

}
