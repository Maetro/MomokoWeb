import { Component, OnInit, Input } from '@angular/core';
import { CustomBlock } from '../common-dtos/custom-block';

@Component({
  selector: 'app-custom-block',
  templateUrl: './custom-block.component.html',
  styleUrls: ['./custom-block.component.scss']
})
export class CustomBlockComponent implements OnInit {

  @Input() customBlock: CustomBlock; 

  constructor() { }

  ngOnInit() {
    console.log(this.customBlock);

  }

}
