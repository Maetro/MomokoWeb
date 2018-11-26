import { Component, OnInit } from "@angular/core";
import { environment } from "environments/environment";

@Component({
    selector: 'app-entry-content',
    templateUrl: './entry-content.component.html',
    styleUrls: ['./entry-content.component.scss']
})
export class EntryContentComponent implements OnInit {

    private log = environment.log;

    constructor() { }

    ngOnInit(): void {
    }
}