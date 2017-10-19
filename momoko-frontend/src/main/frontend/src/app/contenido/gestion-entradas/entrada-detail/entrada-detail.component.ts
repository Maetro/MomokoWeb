import { Component, OnInit, Input, Output, EventEmitter, AfterViewInit, OnDestroy } from '@angular/core';
import { Entrada } from 'app/dtos/entrada';
import { EntradaService } from 'app/services/entrada.service';
import { FileUploadService } from 'app/services/fileUpload.service';
import { Message } from 'primeng/primeng';

@Component({
  selector: 'app-entrada-detail',
  templateUrl: './entrada-detail.component.html',
  styleUrls: ['./entrada-detail.component.css']
})
export class EntradaDetailComponent implements OnInit  {

    @Input() entrada: Entrada;

    @Output() onEntradaGuardada: EventEmitter<Entrada> = new EventEmitter<Entrada>();

    esLibroNuevo = true;

    msgs: Message[] = [];

  constructor(private entradaService: EntradaService, private fileUploadService: FileUploadService) { }

  ngOnInit() {
  }

}
