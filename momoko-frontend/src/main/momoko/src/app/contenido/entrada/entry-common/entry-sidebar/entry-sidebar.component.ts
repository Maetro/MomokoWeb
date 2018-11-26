import { Component, OnInit } from "@angular/core";
import { environment } from "environments/environment";

@Component({
    selector: 'app-entry-sidebar',
    templateUrl: './entry-sidebar.component.html',
    styleUrls: ['./entry-sidebar.component.scss']
})
export class EntrySidebarComponent implements OnInit {

    private log = environment.log;

    constructor() { }

    ngOnInit(): void {
    }
}