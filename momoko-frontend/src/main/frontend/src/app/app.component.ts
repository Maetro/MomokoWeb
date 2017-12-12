import { GeneralDataService } from 'app/services/general-data.service';

import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { Component, OnInit, APP_INITIALIZER } from '@angular/core';

import { Libro } from './dtos/libro';
import { LibroService } from './services/libro.service';
import { FileUploadService } from './services/fileUpload.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers: [LibroService, FileUploadService, GeneralDataService]
})
export class AppComponent implements OnInit {
  constructor() { }

    ngOnInit() {
    }
}

export const API_URL = 'http://192.168.43.117:8080/api';
