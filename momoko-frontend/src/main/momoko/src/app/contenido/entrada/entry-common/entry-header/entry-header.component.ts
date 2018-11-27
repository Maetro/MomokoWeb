import { Component, OnInit, Input } from "@angular/core";
import { environment } from "environments/environment";
import { Entrada } from "app/dtos/entrada";
import { Libro } from "app/dtos/libro";

@Component({
  selector: "app-entry-header",
  templateUrl: "./entry-header.component.html",
  styleUrls: ["./entry-header.component.scss"]
})
export class EntryHeaderComponent implements OnInit {
  
  @Input() entry: Entrada;

  @Input() book: Libro;

  @Input() type: string;

  @Input() subheaderAuthorIntro: string;

  private log = environment.log;

  constructor() {}

  ngOnInit(): void {}
}
