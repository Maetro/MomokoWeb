import { OnInit, Input, Component } from "@angular/core";
import { EntradaSimple } from "app/dtos/entradaSimple";

@Component({
    selector: 'app-related-posts',
    templateUrl: './related-posts.component.html',
    styleUrls: ['./related-posts.component.scss']
  })
  export class RelatedPostsComponent implements OnInit {

    @Input() posts: EntradaSimple[];

    ngOnInit(): void {
    }

  }