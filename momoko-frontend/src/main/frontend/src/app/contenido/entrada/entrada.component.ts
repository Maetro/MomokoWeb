import { EntradaService } from './../../services/entrada.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Etiqueta } from 'app/dtos/etiqueta';
import { Entrada } from 'app/dtos/entrada';

@Component({
  selector: 'app-entrada',
  templateUrl: './entrada.component.html',
  styleUrls: ['./entrada.component.css']
})
export class EntradaComponent implements OnInit, OnDestroy {

  private url: string;

  private suscriptor: any;

  private entrada: Entrada;

  constructor(private entradaService: EntradaService, private route: ActivatedRoute) { }

  ngOnInit() {
    console.log('Creando pagina de la entrada')
    this.suscriptor = this.route.params.subscribe(params => {
      this.url = params['url']; // (+) converts string 'id' to a number
      console.log(this.url);
      this.entradaService.getEntrada(this.url).subscribe(entradaCompleta => {
        console.log(entradaCompleta);
        this.entrada = entradaCompleta;
      })
      // In a real app: dispatch action to load the details here.
   });
  }

  ngOnDestroy() {
    this.suscriptor.unsubscribe();
  }

}
