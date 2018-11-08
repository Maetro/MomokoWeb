import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-join-us-author',
  templateUrl: './join-us-author.component.html',
  styleUrls: ['./join-us-author.component.scss']
})
export class JoinUsAuthorComponent implements OnInit {
  
  @Output()
  return: EventEmitter<String> = new EventEmitter<String>();

  constructor() { }

  ngOnInit() {
  }

  volver() {
    this.return.emit('RETURN');
  }

}
