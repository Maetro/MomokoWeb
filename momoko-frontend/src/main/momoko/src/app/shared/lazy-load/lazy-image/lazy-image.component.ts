import { Component, Input } from "@angular/core";

@Component({
    selector: 'app-lazy-image',
    templateUrl: './lazy-image.component.html',
    styleUrls: ['./lazy-image.component.scss']
  })
  export class LazyImageComponent{

    @Input() image: string;
    @Input() alt: string;
    @Input() name: string;
    @Input() height: number = 1;
    @Input() class: string = '';

    showImage = false;

    downloadImage(){
      console.debug('Download image: '+ this.name);
      this.showImage = true;
    }

  }