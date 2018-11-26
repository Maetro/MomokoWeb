import { Component, OnInit } from "@angular/core";
import { environment } from "environments/environment";

@Component({
    selector: 'app-entry-footer',
    templateUrl: './entry-footer.component.html',
    styleUrls: ['./entry-footer.component.scss']
})
export class EntryFooterComponent implements OnInit {

    private log = environment.log;

    constructor() { }

    ngOnInit(): void {
    }
}