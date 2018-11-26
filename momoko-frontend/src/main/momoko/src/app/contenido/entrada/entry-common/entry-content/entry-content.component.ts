import { Component, OnInit, Input } from "@angular/core";
import { environment } from "environments/environment";
import { Entrada } from "app/dtos/entrada";

@Component({
    selector: 'app-entry-content',
    templateUrl: './entry-content.component.html',
    styleUrls: ['./entry-content.component.scss']
})
export class EntryContentComponent implements OnInit {

    @Input() entry: Entrada;

    private log = environment.log;

    content: string;

    htmlContent: string[];
  
    bookTemplates: string[];

    constructor() { }

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
}