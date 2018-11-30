import { DatoEntrada } from '../../../dtos/datoEntrada';
import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Libro } from '../../../dtos/libro';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-book-menu',
  templateUrl: './book-menu.component.html',
  styleUrls: ['./book-menu.component.scss']
})
export class BookMenuComponent implements OnInit {
  private log = environment.log;

  urlVideo: string;
  urlAnalisis: string;
  urlNoticia: string;
  urlMiscelaneo: string;
  hayAnalisis = false;
  numNoticias = 0;
  numMiscelaneos = 0;
  numVideos = 0;
  esSaga = false;
  menuLibroExtra: DatoEntrada[];

  @Input() parentType: string;
  @Input() datosEntrada: DatoEntrada[];
  @Input() libro: Libro;

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    if (this.log) {
      console.log('Iniciando menu');
    }
    if (this.libro.saga != null) {
      this.esSaga = true;
    }
    if (this.datosEntrada.length > 0) {
      if (this.log) {
        console.log('Iniciando menu');
      }
      this.datosEntrada.forEach(entrada => {
        switch (entrada.tipoEntrada) {
          // 1 - NOTICIA
          case 1: {
            this.numNoticias++;
            this.urlNoticia = entrada.urlEntrada;
            break;
          }
          // 2 - ANALISIS
          case 2: {
            this.hayAnalisis = true;
            this.urlAnalisis = entrada.urlEntrada;
            break;
          }
          // 3 - MISCELANEO
          case 3: {
            this.numMiscelaneos++;
            this.urlMiscelaneo = entrada.urlEntrada;
            break;
          }
          // 4 -VIDEO
          case 4: {
            this.numVideos++;
            this.urlVideo = entrada.urlEntrada;
          }
          default: {
            break;
          }
        }
        if (entrada.enMenu) {
          if (this.menuLibroExtra == null) {
            this.menuLibroExtra = Array();
          }
          this.menuLibroExtra.push(entrada);
        }
      });

    }
  }

  isActive(instruction: string): boolean {
    // Set the second parameter to true if you want to require an exact match. 
      return instruction === this.parentType;
  }
}