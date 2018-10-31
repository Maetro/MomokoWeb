import { Component, OnInit, Input } from '@angular/core';
import { SocialData } from 'app/dtos/abstract/socialData';

@Component({
  selector: 'app-social-data-icons',
  templateUrl: './social-data-icons.component.html',
  styleUrls: ['./social-data-icons.component.scss']
})
export class SocialDataIconsComponent implements OnInit {

  @Input() socialData: SocialData;

  constructor() { }

  ngOnInit() {
  }

}
