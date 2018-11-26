import { Component, OnInit } from "@angular/core";
import { environment } from "environments/environment";

@Component({
    selector: 'app-entry-header',
    templateUrl: './entry-header.component.html',
    styleUrls: ['./entry-header.component.scss']
})
export class EntryHeaderComponent implements OnInit {

    private log = environment.log;

    constructor() { }

    ngOnInit(): void {
    }
}