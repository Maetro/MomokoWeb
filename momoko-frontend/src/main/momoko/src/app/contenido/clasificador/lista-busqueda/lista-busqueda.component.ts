import { ResultadoBusqueda } from './../../../dtos/resultadoBusqueda';
import { Component, OnInit } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { ClasificadorService } from '../../../services/clasificador.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ObtenerPaginaBusquedaResponse } from '../../../dtos/response/obtenerPaginaBusquedaResponse';

@Component({
  selector: 'app-lista-busqueda',
  templateUrl: './lista-busqueda.component.html',
  styleUrls: ['./lista-busqueda.component.css']
})
export class ListaBusquedaComponent implements OnInit {

 private log = environment.log;

  private url: string;

  suscriptor: any;

  resultadosBusqueda: ResultadoBusqueda[];

  parametrosBusqueda: string;

  enLista: boolean;

  numeroEntradas: number;

  numeroEntradasPagina = 9;

  numeroPaginas: number;

  numbers

  constructor(private clasificadorService: ClasificadorService,  private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    if (this.log) {
      console.log('Creando pagina de la categoria');
    }
    this.enLista = false;
    this.suscriptor = this.route.params.subscribe(params => {
      this.url = params['url_categoria']; // (+) converts string 'id' to a number
      if (this.log) {
        console.log(this.url);
      }
      this.route.data.subscribe((data: { busqueda: ObtenerPaginaBusquedaResponse }) => {
        this.parametrosBusqueda = data.busqueda.parametrosBusqueda;
        this.resultadosBusqueda = data.busqueda.resultados;
        this.numeroEntradas = data.busqueda.numeroEntradas;
      });



      // In a real app: dispatch action to load the details here.
    });
  }

  ngOnDestroy() {
    this.suscriptor.unsubscribe();
  }

  onChangeOrder(event) {
    if (this.log) {
      console.log(event);
    }
  }

  activarEnLista() {
    this.enLista = true;
  }

  desactivarEnLista() {
    this.enLista = false;
  }
}
