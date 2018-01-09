import { Component, OnInit, Input } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Libro } from '../../../dtos/libro';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-menu-interno-libro',
  templateUrl: './menu-interno-libro.component.html',
  styleUrls: ['./menu-interno-libro.component.css']
})
export class MenuInternoLibroComponent implements OnInit {

  private log = environment.log;

  urlVideo: string;
  urlAnalisis: string;
  hayVideo = false;
  hayAnalisis = false;
  hayNoticias = false;
  hayGuia = false;


  @Input() libro: Libro;

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    if (this.log) {
      console.log('Iniciando menu');
    }
    if (this.libro.entradasLibro.length > 0) {
      this.libro.entradasLibro.forEach(entrada => {
        switch (entrada.tipoEntrada) {
          // 1 - NOTICIA
          case 1: {
            this.hayNoticias = true;
            break;
          }
          // 2 - ANALISIS
          case 2: {
            this.hayAnalisis = true;
            this.urlAnalisis = entrada.urlEntrada;
            break;
          }
          // 3 - VIDEO
          case 3: {
            this.hayVideo = true;
            this.urlVideo = entrada.urlEntrada;
            break;
          }
          default: {
            break;
          }
        };
      })
    }

  }

  isActive(instruction: any[]): boolean {
    // Set the second parameter to true if you want to require an exact match.
    return this.router.isActive(this.router.createUrlTree(instruction), true);
  }


}
