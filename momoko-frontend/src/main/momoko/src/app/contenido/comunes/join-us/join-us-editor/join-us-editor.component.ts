import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-join-us-editor',
  templateUrl: './join-us-editor.component.html',
  styleUrls: ['./join-us-editor.component.scss']
})
export class JoinUsEditorComponent implements OnInit {

  @Output()
  return: EventEmitter<String> = new EventEmitter<String>();

  constructor() { }

  ngOnInit() {
  }

  volver() {
    this.return.emit('RETURN');
  }

}
