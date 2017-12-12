import { Component, OnInit, OnDestroy } from '@angular/core';
import { ObtenerPaginaCategoriaResponse } from 'app/dtos/response/obtenerPaginaCategoriaResponse';
import { EntradaSimple } from 'app/dtos/entradaSimple';
import { Categoria } from 'app/dtos/categoria';
import { ClasificadorService } from 'app/services/clasificador.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-lista-categoria',
  templateUrl: './lista-categoria.component.html',
  styleUrls: ['./lista-categoria.component.css']
})
export class ListaCategoriaComponent implements OnInit, OnDestroy {

    private url: string;

    suscriptor: any;

    entradasCategoria: EntradaSimple[];

    categoria: Categoria;

    enLista: boolean;

    constructor(private clasificadorService: ClasificadorService, private route: ActivatedRoute, private router: Router) { }

    ngOnInit() {

      console.log('Creando pagina del genero')
      this.enLista = false;
      this.suscriptor = this.route.params.subscribe(params => {
        this.url = params['url']; // (+) converts string 'id' to a number
        console.log(this.url);

        this.route.data.subscribe((data: { paginaCategoriaResponse: ObtenerPaginaCategoriaResponse }) => {
          this.entradasCategoria = data.paginaCategoriaResponse.entradasCategoria;
          this.categoria = data.paginaCategoriaResponse.categoria;
        });
        // In a real app: dispatch action to load the details here.
      });
    }

    ngOnDestroy() {
      this.suscriptor.unsubscribe();
    }

    onChangeOrder(event) {
      console.log(event);
    }

    activarEnLista() {
      this.enLista = true;
    }

    desactivarEnLista() {
      this.enLista = false;
    }
  }
