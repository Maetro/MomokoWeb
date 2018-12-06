import { Component, Input } from "@angular/core";

@Component({
    selector: 'app-lazy-image-with-link',
    templateUrl: './lazy-image-with-link.component.html',
    styleUrls: ['./lazy-image-with-link.component.scss']
  })
  export class LazyImageWithLinkComponent{

    @Input() link: string;
    @Input() image: string;
    @Input() alt: string;
    @Input() name: string;
    @Input() msg: string = '';
    @Input() height: number = 1;

    showImage = false;

    downloadImage(){
      console.debug('Download image: '+ this.name);
      this.showImage = true;
    }

  }