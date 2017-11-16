import { Comentario } from 'app/dtos/comentario';
import { Router } from '@angular/router';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { EntradaService } from 'app/services/entrada.service';
import { ActivatedRoute } from '@angular/router';
import { Libro } from 'app/dtos/libro';
import { LibroSimple } from 'app/dtos/libroSimple';
import { FichaEntrada } from 'app/dtos/fichaEntrada';

@Component({
  selector: 'app-analisis',
  templateUrl: './analisis.component.html',
  styleUrls: ['./analisis.component.css']
})

export class AnalisisComponent implements OnInit, OnDestroy {

  private url: string;

    suscriptor: any;

    entrada: Entrada;

    autores: string

    @Input() titulo: string;

    librosParecidos: LibroSimple[];

    comentarios: Comentario[];

    constructor(private entradaService: EntradaService, private route: ActivatedRoute, private router: Router) { }

    ngOnInit() {
      console.log('Creando pagina de la entrada')
      this.suscriptor = this.route.params.subscribe(params => {
        this.url = params['url']; // (+) converts string 'id' to a number
        console.log(this.url);
        this.autores = '';
        this.route.data.subscribe((data: { fichaEntrada: FichaEntrada }) => {
          this.entrada = data.fichaEntrada.entrada;
          this.librosParecidos = data.fichaEntrada.cincoLibrosParecidos;
          this.comentarios = data.fichaEntrada.comentarios;
          this.entrada.libroEntrada.autores.forEach(autor => {
            this.autores += autor.nombre + ', ';
          });
          this.autores = this.autores.substring(0, this.autores.length - 2);
        });
        // In a real app: dispatch action to load the details here.
     });
    }

    ngOnDestroy() {
      this.suscriptor.unsubscribe();
    }

    isActive(instruction: any[]): boolean {
      // Set the second parameter to true if you want to require an exact match.
      return this.router.isActive(this.router.createUrlTree(instruction), false);
    }

}
