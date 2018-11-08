import { Component, OnInit } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-join-us',
  templateUrl: './join-us.component.html',
  styleUrls: ['./join-us.component.scss']
})
export class JoinUsComponent implements OnInit {
  authorSelected = false;
  editorSelected = false;
  publisherSelected = false;

  constructor() {}

  ngOnInit() {}

  return($event: string) {
    if ($event === 'RETURN') {
      this.authorSelected = false;
      this.editorSelected = false;
      this.publisherSelected = false;
    } else if ($event === 'SEND'){
      this.close();
    }
  }

  close() {
    $('#bookTemplateModal').modal('hide');
  }
}
