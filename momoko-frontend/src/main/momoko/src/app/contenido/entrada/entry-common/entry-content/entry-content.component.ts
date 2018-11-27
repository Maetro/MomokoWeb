import { Component, OnInit, Input, AfterViewChecked, AfterViewInit, Inject, PLATFORM_ID, ViewChild, TemplateRef } from "@angular/core";
import { environment } from "environments/environment";
import { Entrada } from "app/dtos/entrada";
import { isPlatformBrowser } from "@angular/common";

declare var $: any;
@Component({
    selector: 'app-entry-content',
    templateUrl: './entry-content.component.html',
    styleUrls: ['./entry-content.component.scss']
})
export class EntryContentComponent implements OnInit, AfterViewInit {


    @Input() entry: Entrada;

    private log = environment.log;

    content: string;

    htmlContent: string[];
  
    bookTemplates: string[];

    @ViewChild('book-template-angular')
    bookTemplate: TemplateRef<any>;

    constructor(@Inject(PLATFORM_ID) private platformId: Object,) { }

    ngOnInit(): void {


        this.htmlContent = new Array();
        this.bookTemplates = new Array();
        if (this.entry.contenidoEntrada.indexOf('book-template-angular') != -1) {
          let content = this.entry.contenidoEntrada;
          let cont = 1;
          while (content.indexOf('book-template-angular') != -1) {
            const begin = content.indexOf(
              '<book-template-angular'
            );
            const end = content.indexOf(
              '</book-template-angular>'
            );
            this.htmlContent.push(
              content.substring(0, begin)
            );
            
            const book = content.substring(begin, end);
            this.htmlContent.push(
              "<div id=\"bookTemplate" + cont +"\" class=\"bookTemplate" + cont +"\">Book" + cont +"</div>"
            );
            
            this.bookTemplates.push(book);
            content = content.substring(end + 24);
            cont++;
          }
          this.htmlContent.push(content);
        } else {
          this.htmlContent.push(this.entry.contenidoEntrada);
        }
        this.content = "";
        this.htmlContent.forEach(content => {
          this.content += content; 
        });
        
    }

    ngAfterViewInit(): void {
      if (isPlatformBrowser(this.platformId)) {
        if (this.log) {
          console.log('Ejecutando JQuery');
        }
        $('.light-gallery').lightGallery({
          thumbnail: false,
          selector: '.lgitem',
          animateThumb: true,
          showThumbByDefault: false,
          download: false,
          autoplayControls: false,
          zoom: false,
          fullScreen: false,
          thumbWidth: 100,
          thumbContHeight: 80,
          hash: false,
          videoMaxWidth: '1000px'
        });
        setTimeout(() => this.crearCollage(), 2000);
      }
    }

    crearCollage() {
      $('.collage').attr('id', 'collage-large');
      if (this.log) {
        console.log('COLLAGE');
      }
      this.collage();
      $('.collage .collage-image-wrapper').css('opacity', 0);
      $('.overlay a').prepend('<span class="over"><span></span></span>');
    }
  
    collage() {
      $('.collage')
      .removeWhitespace()
      .collagePlus({
        fadeSpeed: 5000,
        targetHeight: 400,
        effect: 'effect-2',
        direction: 'vertical',
        allowPartialLastRow: true
      });
    }
}