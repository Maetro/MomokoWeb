import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-join-us',
  templateUrl: './join-us.component.html',
  styleUrls: ['./join-us.component.scss']
})
export class JoinUsComponent implements OnInit {
  
  authorSelected = false;
  editorSelected = false;
  publisherSelected = false;
  
  constructor() { }

  ngOnInit() {
  }

  return(){
    this.authorSelected = false;
    this.editorSelected = false;
    this.publisherSelected = false;
  }

  
}
