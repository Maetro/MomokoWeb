import { Component, OnInit, Input } from "@angular/core";
import { EntradaSimple } from "app/dtos/entradaSimple";

@Component({
    selector: 'app-index-header',
    templateUrl: './index-header.component.html',
    styleUrls: ['./index-header.component.scss']
  })
  export class IndexHeaderComponent implements OnInit{

    @Input() opinions: EntradaSimple[];
    @Input() miscellaneous: EntradaSimple[];
    @Input() news: EntradaSimple[];
    ngOnInit(): void {
        
    }

  }