import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-join-us-publisher',
  templateUrl: './join-us-publisher.component.html',
  styleUrls: ['./join-us-publisher.component.scss']
})
export class JoinUsPublisherComponent implements OnInit {

  @Output()
  return: EventEmitter<String> = new EventEmitter<String>();

  constructor() { }

  ngOnInit() {
  }

  volver() {
    this.return.emit('RETURN');
  }

}
