import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Message, SelectItem } from 'primeng/primeng';
import { FilterService } from '../filter.service';
import { Filter } from '../../../../dtos/filter/filter';
import { Genero } from '../../../../dtos/genre/genero';
import { LibroService } from '../../../../services/libro.service';
import { UtilService } from '../../../../services/util/util.service';
import { FilterRuleType } from '../../../../dtos/filter/filter-rule-type.enum';
import { environment } from '../../../../../environments/environment';

@Component({
  selector: 'app-create-filter',
  templateUrl: './create-filter.component.html',
  styleUrls: ['./create-filter.component.css']
})
export class CreateFilterComponent implements OnInit {
  private log = environment.log;

  filter: Filter;

  changeLog: string[] = [];

  esRedactorNuevo = false;

  msgs: Message[] = [];

  customURL = false;

  filterTypes: SelectItem[];

  filterTypeSelected: string;

  refencedValueOptions: SelectItem[];

  referencedValueSelected: string;

  filterValues: string[];

  genres: Genero[];

  constructor(
    private filterService: FilterService,
    private libroService: LibroService,
    private util: UtilService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.filter = new Filter();
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
    if (this.filterValues && this.filterValues.length > 0) {
      this.filter.possibleValues = this.filterValues;
    }
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

  public needToDefineCustomValues(): boolean {
    return this.filterService.needToDefineCustomValues(this.filter);
  }

  changeFilterType(){
    console.log('changeFilterType');
  }

}
