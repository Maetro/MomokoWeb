import { Component, OnInit, ViewChild } from '@angular/core';
import { environment } from 'environments/environment';
import { Filter } from '../../../dtos/filter/filter';
import { FilterService } from '../filter.service';
import { Router } from '@angular/router';
import { FilterRuleType } from '../../../dtos/filter/filter-rule-type.enum';
import { Genero } from '../../../dtos/genero';

@Component({
  selector: 'app-filter-list',
  templateUrl: './filter-list.component.html',
  styleUrls: ['./filter-list.component.css']
})
export class FilterListComponent implements OnInit {
  private log = environment.log;

  selectedFilter: Filter;

  cols: any[];

  loading: boolean;

  title = 'Libros';
  filters: Filter[];

  constructor(private filterService: FilterService, private router: Router) {
    if (this.log) {
      console.log('Builder ListaFilteresComponent');
    }
    this.filters = [];
  }

  getFilters(): void {
    if (this.log) {
      console.log('service -> getFilteres()');
    }
    this.filterService.getFilters().subscribe(filters => {
      filters.forEach(filter => {
        this.filters = [...this.filters, filter];
      });
    });
  }

  ngOnInit(): void {
    if (this.log) {
      console.log('ngOnInit Lista getFilteres');
    }
    this.loading = true;
    this.filterService.getFilters().subscribe(filtersP => {
      const filtersList = filtersP;
      filtersList.forEach(filter => {
        this.filters = [...this.filters, filter];
      });
      this.loading = false;
    });
    this.cols = [
      { field: 'nameFilter', header: 'Nombre' },
      { field: 'urlFilter', header: 'Url' },
      { field: 'stringOfFilterType', header: 'Tipo' },
      { field: 'referencedProperty', header: 'Propiedad' },
      { field: 'possibleValues', header: 'Valores posibles' },
      { field: 'genres', header: 'Géneros' }
    ];
  }

  newFilter(): void {
    if (this.log) {
      console.log('nuevoFilter');
    }
    this.router.navigate(['/admin/nuevo-filtro']);
  }

  editFilter(filter: any) {
    this.router.navigate(['/admin/editar-filtro/' + filter.urlFilter]);
  }

  volver(): void {
    if (this.log) {
      console.log('volver');
    }
  }

  actualizarOAnadirFilter(filter: Filter): void {
    if (this.log) {
      console.log('actualizarOAnadirFilter ' + filter);
    }
    let encontrado = false;
    this.filters.forEach(red => {
      if (red.filterId === filter.filterId) {
        red = filter;
        encontrado = true;
      }
    });
    if (!encontrado) {
      this.filters = [...this.filters, filter];
    }
  }

  onRowSelect(event) {
    if (this.log) {
      console.log('onRowSelect');
    }
  }

  public getStringOfFilterType(filterType: string): string {
    return this.filterService.getStringOfFilterType(filterType);
  }

  public getStringOfPossibleValues(posibleValues: string[]): string {
    let result = '';
    if (posibleValues && posibleValues.length > 0) {
      posibleValues.forEach(posibleValue => {
        result += posibleValue + ', ';
      });
      result = result.substring(0, result.length - 2);
    }

    return result;
  }

  public getGenresString(generos: Genero[]): string {
    let result = '';
    if (generos && generos.length > 0) {
      generos.forEach(genero => {
        result += genero.nombre + ', ';
      });
      result = result.substring(0, result.length - 2);
    } else {
      result = 'Todos';
    }
    return result;
  }


}
