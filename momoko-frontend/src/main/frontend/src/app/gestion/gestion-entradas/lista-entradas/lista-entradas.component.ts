import { Etiqueta } from 'app/dtos/etiqueta';
import { EntradaDetailComponent } from './../entrada-detail/entrada-detail.component';
import { Entrada } from 'app/dtos/entrada';
import { Component, OnInit, ViewChild } from '@angular/core';

import { EntradaService } from 'app/services/entrada.service';
import { FileUploadService } from 'app/services/fileUpload.service';


@Component({
  selector: 'app-lista-entradas',
  templateUrl: './lista-entradas.component.html',
  styleUrls: ['./lista-entradas.component.css']
})
export class ListaEntradasComponent implements OnInit {

      loading: boolean;

      title = 'Libros';
      entradas: Entrada[];
      selectedEntrada: Entrada;

      @ViewChild(EntradaDetailComponent) entradaDetailComponent: EntradaDetailComponent;

      constructor(private entradasService: EntradaService, private fileUploadService: FileUploadService) {
        this.entradas = [];

      }

      getEntradas(): void {
        console.log('service -> getEntradas()')
        this.entradas = [];
        this.entradasService.getAllEntradas().subscribe(entradas => {
          entradas.forEach(entrada => {
            this.entradas =  [ ...this.entradas, entrada ];
          });

        });
      }

      ngOnInit(): void {
        console.log('ngOnInit Lista entradas')
        this.loading = true;
        this.entradas = [];
        this.entradasService.getAllEntradas().subscribe(entradas => {
          const entradasList = entradas
          entradasList.forEach(entrada => {
            this.entradas =  [ ...this.entradas, entrada ];
          });
          this.loading = false;
        });
      }

      handleRowSelect(event: any) {
        console.log('Seleccionando entrada');
        let entrada: Entrada;
        entrada = event.data;
        this.entradaDetailComponent.etiquetas = [];
        this.entradasService.getEntrada(entrada.urlEntrada).subscribe(entradaCompleta => {
          console.log(entradaCompleta);
          entradaCompleta.entrada.etiquetas.forEach((etiqueta: Etiqueta) => {
            this.entradaDetailComponent.etiquetas.push(etiqueta.nombreEtiqueta);
          });
        });
        console.log(entrada);
      }

      selectEntrada(entrada: Entrada) {
        console.log(entrada);
        this.selectedEntrada = entrada;
      }

      nuevaEntrada(): void {
        this.selectedEntrada = null;
        const entrada = new Entrada;
        entrada.etiquetas = [];
        this.selectedEntrada = entrada;
      }

      volver(): void {
        this.selectedEntrada = null;
      }

      actualizarOAnadirEntrada(entrada: Entrada): void {
        this.selectedEntrada = null;
        this.entradas = [];
        this.getEntradas();
        console.log(entrada);
      }
}
