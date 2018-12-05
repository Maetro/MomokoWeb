import { Component, Input } from "@angular/core";

@Component({
    selector: 'app-lazy-image',
    templateUrl: './lazy-image.component.html',
    styleUrls: ['./lazy-image.component.scss']
  })
  export class LazyImageComponent{

    @Input() link: string;
    @Input() image: string;
    @Input() alt: string;

    showImage = false;
  }