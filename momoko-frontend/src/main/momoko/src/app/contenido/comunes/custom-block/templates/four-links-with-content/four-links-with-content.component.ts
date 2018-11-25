import { Component, OnInit, Input } from '@angular/core';
import { CustomBlock } from 'app/contenido/comunes/common-dtos/custom-block';

@Component({
  selector: 'app-four-links-with-content',
  templateUrl: './four-links-with-content.component.html',
  styleUrls: ['./four-links-with-content.component.scss']
})
export class FourLinksWithContentComponent implements OnInit {

  @Input() customBlock: CustomBlock;

  constructor() { }

  ngOnInit() {
    

  }

}
