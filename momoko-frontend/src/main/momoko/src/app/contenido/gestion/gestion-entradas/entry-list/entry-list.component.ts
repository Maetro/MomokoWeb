import { OnInit, ViewChild, Component } from "@angular/core";
import { environment } from "environments/environment";
import { Entrada, EntryTypeEnum } from "../../../../dtos/entrada";
import { Table } from "primeng/table";
import { EntryService } from "../entry.service";
import { Router } from "@angular/router";
import { Globals } from "../../../../app.globals";
import { EntradaSimple } from "../../../../dtos/entradaSimple";

@Component({
    selector: 'app-entry-list',
    templateUrl: './entry-list.component.html',
    styleUrls: ['./entry-list.component.scss']
  })
  export class EntryListComponent implements OnInit {
    
    private log = environment.log;
  
    selectedEntry: EntradaSimple;
  
    @ViewChild(Table) table: Table;
  


    cols: any[];
  
    loading: boolean;
  
    title = 'Libros';
    entries: EntradaSimple[];
  
    filter: string;
  
    constructor(private entryService: EntryService, private router: Router, private globals: Globals) {
      if (this.log) {
        console.log('Builder BookListComponent');
      }
      this.entries = [];
      this.filter = globals.entryfilter;
    }
  
    getBooks(): void {
      if (this.log) {
        console.log('service -> getEntries()');
      }
      this.entryService.getEntries().subscribe(entries => {
        entries.forEach(entry => {
          this.entries = [...this.entries, entry];
        });
      });
    }
  
    ngOnInit(): void {
      if (this.log) {
        console.log('ngOnInit Lista getBooks');
      }
      this.entryService.getEntries().subscribe(entries => {
        const entryList = entries;
        entryList.forEach(entry => {
          this.entries = [...this.entries, entry];
        });
        this.table.filterGlobal(this.globals.entryfilter, 'contains');
        this.loading = false;
      });

      this.cols = [
        { field: 'tipoEntrada', header: 'Tipo entrada' },
        { field: 'tituloEntrada', header: 'Título'},
        { field: 'titulosLibros', header: 'Libro Asociado'},
        { field: 'urlEntrada', header: 'URL'},
        { field: 'nombreAutor', header: 'Autor'},
        { field: 'fechaAlta', header: 'Fecha publicación'},
      ];
      
  
    }
  
    newEntry(): void {
      if (this.log) {
        console.log('nuevoBook');
      }
      this.router.navigate(['/gestion/nueva-entrada']);
    }
  
    editEntry(entry: EntradaSimple) {
      this.router.navigate(['/gestion/editar-entrada/' + entry.urlEntrada]);
    }
    
    actualizarOAnadirBook(entry: EntradaSimple): void {
      if (this.log) {
        console.log('actualizarOAnadirBook ' + entry);
      }
      let encontrado = false;
      this.entries.forEach(entryP => {
        if (entryP.urlEntrada === entry.urlEntrada) {
            entryP = entry;
          encontrado = true;
        }
      });
      if (!encontrado) {
        this.entries = [...this.entries, entry];
      }
    }
  
    onRowSelect(event) {
      if (this.log) {
        console.log('onRowSelect');
      }
    }
  
    onChangeFilter($event){
      console.log($event.target.value);
      this.globals.bookfilter = $event.target.value;
    }
  
  }