import { Component, OnInit, Inject, PLATFORM_ID, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Title, Meta } from '@angular/platform-browser';
import { environment } from '../../../../../environments/environment';
import { Saga } from '../../../../dtos/saga';

@Component({
  selector: 'app-descriptor-saga',
  templateUrl: './descriptor-saga.component.html',
  styleUrls: ['./descriptor-saga.component.css']
})
export class DescriptorSagaComponent implements OnInit {
  
  private log = environment.log;

  @Input() saga: Saga;
  @Input() urlAnalisis: string;

  constructor(
  ) {}

  ngOnInit() {
    
  }

}
