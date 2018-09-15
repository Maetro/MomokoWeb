import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Message, SelectItem } from 'primeng/primeng';

import { FilterService } from '../filter.service';
import { Filter } from '../../../../dtos/filter/filter';
import { Genero } from '../../../../dtos/genre/genero';
import { LibroService } from '../../../../services/libro.service';
import { UtilService } from '../../../../services/util/util.service';
import { FilterRuleType } from '../../../../dtos/filter/filter-rule-type.enum';
import { environment } from 'environments/environment';
import { NameValue } from '../../../../dtos/filter/name-value';
import { FilterValue } from '../../../../dtos/filter/filter-value';

@Component({
  selector: 'app-filter-form',
  templateUrl: './filter-form.component.html',
  styleUrls: ['./filter-form.component.css']
})
export class FilterFormComponent implements OnInit {
  private log = environment.log;

  filterUrl: string;

  filter: Filter;

  changeLog: string[] = [];

  esRedactorNuevo = false;

  msgs: Message[] = [];

  customURL = false;

  filterTypes: SelectItem[];

  filterTypeSelected: string;

  refencedValueOptions: SelectItem[];

  referencedValueSelected: string;

  filterValues: FilterValue[];

  genres: Genero[];

  cols: any[];

  constructor(
    private filterService: FilterService,
    private libroService: LibroService,
    private util: UtilService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.filterUrl = this.route.snapshot.paramMap.get('url');

    this.cols = [
      { field: 'order', header: 'ORDEN' },
      { field: 'name', header: 'NOMBRE' },
      { field: 'value', header: 'VALOR' }
  ];

    this.filterService.getFilterByUrl(this.filterUrl).subscribe(filter => {
      this.filter = filter;
      this.filterTypeSelected = this.filter.filterType;
      this.genres = this.filter.genres;
      this.referencedValueSelected = this.filter.referencedProperty;
      this.filterValues = this.filter.filterValues;
    });
    this.filterTypes = [];
    this.genres = [];
    this.filterTypes.push({
      label: 'Selecciona un tipo de filtro',
      value: null
    });
    const types = Object.keys(FilterRuleType);
    types.forEach(type => {
      this.filterTypes.push({
        label: this.getStringOfFilterType(type),
        value: type
      });
    });
    this.refencedValueOptions = this.filterService.createReferenceValueOptions();
    this.libroService.getGeneros().subscribe(generos => {
      generos.forEach(genero => {
        this.genres = [...this.genres, genero];
      });
    });
  }

  saveFilter(): void {
    this.filter.filterType = FilterRuleType[this.filterTypeSelected];
    this.filter.referencedProperty = this.referencedValueSelected;
    if (this.filterValues){
      let cont = 1;
      this.filterValues.forEach(filter => {
        filter.order = cont;
        cont++;
      });
    }
    this.filter.filterValues = this.filterValues;
    this.filterService.saveFilter(this.filter).subscribe(res => {
      if (res.estadoGuardado === 'CORRECTO') {
        this.showSuccess('Filtro guardado correctamente');
        this.router.navigate(['/gestion/lista-filtros']);
      } else {
        this.showError(res.listaErroresValidacion);
        console.log(res.exception);
      }
    });
  }

  volver() {
    this.showSuccess('Filtro guardado correctamente');
    this.router.navigate(['/gestion/lista-filtros']);
  }

  showSuccess(mensaje: string) {
    this.msgs = [];
    if (this.log) {
      console.log(mensaje);
    }
    this.msgs.push({ severity: 'success', summary: 'OK', detail: mensaje });
  }

  showError(mensaje: string[]) {
    this.msgs = [];
    if (this.log) {
      console.log(mensaje);
    }
    let mensajeTotal = '';
    mensaje.forEach(element => {
      mensajeTotal += element + '<br/>';
    });
    if (this.log) {
      console.log(mensajeTotal);
    }
    this.msgs.push({
      severity: 'error',
      summary: 'ERROR',
      detail: mensajeTotal
    });
  }

  changeName(newValue: string) {
    if (!this.customURL) {
      this.filter.urlFilter = encodeURIComponent(
        this.util.convertToSlug(newValue)
      );
    }
  }

  public getStringOfFilterType(filterType: string): string {
    return this.filterService.getStringOfFilterType(filterType);
  }

  addOption(){
    const fv = new FilterValue()
    fv.name = '';
    fv.value = '';
    this.filterValues.push(fv);
  }
}
