import { EntradaSimple } from 'app/dtos/entradaSimple';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { EntradaService } from 'app/services/entrada.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Entrada } from 'app/dtos/entrada';
import { LibroSimple } from 'app/dtos/libroSimple';
import { Comentario } from 'app/dtos/comentario';
import { ObtenerEntradaResponse } from 'app/dtos/response/obtenerEntradaResponse';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.css']
})
export class MainContentComponent implements OnInit, OnDestroy {

  private url: string;

  entrada: Entrada;

  suscriptor: any;

  librosParecidos: LibroSimple[];

  comentarios: Comentario[];

  entradaAnteriorYSiguiente: EntradaSimple[];

  cuatroPostPequenosConImagen: EntradaSimple[];

  tipoEntrada: number;

  constructor(private entradaService: EntradaService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    console.log('Creando pagina de la entrada')
    this.suscriptor = this.route.params.subscribe(params => {
      this.url = params['url']; // (+) converts string 'id' to a number
      console.log(this.url);
      this.route.data.subscribe((data: { obtenerEntradaResponse: ObtenerEntradaResponse }) => {
        this.entrada = data.obtenerEntradaResponse.entrada;
        this.librosParecidos = data.obtenerEntradaResponse.cincoLibrosParecidos;
        this.comentarios = data.obtenerEntradaResponse.comentarios;
        this.tipoEntrada = data.obtenerEntradaResponse.entrada.tipoEntrada;
        this.entradaAnteriorYSiguiente = data.obtenerEntradaResponse.obtenerEntradaAnteriorYSiguiente;
        this.cuatroPostPequenosConImagen = data.obtenerEntradaResponse.cuatroPostPequenosConImagen;
        // this.entrada.libroEntrada.forEach(libro => {
        //   libro.autores.forEach(autor => {
        //     this.autores += autor.nombre + ', ';
        //   });
        // });
        // this.autores = this.autores.substring(0, this.autores.length - 2);
      });
      // In a real app: dispatch action to load the details here.
   });
  }

  isActive(instruction: any[]): boolean {
    // Set the second parameter to true if you want to require an exact match.
    return this.router.isActive(this.router.createUrlTree(instruction), false);
  }

  ngOnDestroy() {
    this.suscriptor.unsubscribe();
  }

}
