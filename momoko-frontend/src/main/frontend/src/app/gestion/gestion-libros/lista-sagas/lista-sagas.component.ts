import { Component, OnInit } from '@angular/core';
import { environment } from 'environments/environment';
import { Saga } from '../../../dtos/saga';
import { FileUploadService } from '../../../services/fileUpload.service';
import { SagaService } from '../../../services/saga.service';
import { UtilService } from '../../../services/util.service';

@Component({
  selector: 'app-lista-sagas',
  templateUrl: './lista-sagas.component.html',
  styleUrls: ['./lista-sagas.component.css']
})
export class ListaSagasComponent implements OnInit {

  private log = environment.log;

  loading: boolean;

  title = 'Sagas';
  sagas: Saga[];

  selectedSaga: Saga;

  constructor(
    private sagaService: SagaService
  ) {
    this.sagas = [];

  }

  getSagas(): void {
    this.sagaService.getSagas().subscribe(sagas => {
      sagas.forEach(saga => {
        this.sagas = [...this.sagas, saga];
      });

    });
  }

  ngOnInit(): void {
    this.loading = true;
    this.sagaService.getSagas().subscribe(librosP => {
      const librosList = librosP
      librosList.forEach(element => {
        this.sagas = [...this.sagas, element];
      });
      this.loading = false;
    });
  }

  selectSaga(saga: Saga) {
    if (this.log) {
      console.log(saga);
    }
    this.selectedSaga = saga;
  }

  nuevaSaga(): void {
    this.selectedSaga = null;
    const saga = new Saga;
    saga.librosSaga = [];
    saga.urlsLibrosSaga = [];
    saga.estaTerminada = false;
    saga.tipoSaga = 'Saga';
    saga.dominaLibros = false;
    this.selectedSaga = saga;
  }

  volver(): void {
    this.selectedSaga = null;
  }

  actualizarOAnadirSaga(saga: Saga): void {
    if (this.log) { console.log(saga); }
    this.selectedSaga = null;
    this.sagas = [];
    this.getSagas();
    if (this.log) {
      console.log(saga);
    }
  }

  obtenerSagas(): void {

    this.sagaService.getSagas().subscribe(sagasP => {
      const librosList = sagasP
      librosList.forEach(saga => {
        this.sagas = [...this.sagas, saga];
      });
      this.loading = false;
    });
  }


}
