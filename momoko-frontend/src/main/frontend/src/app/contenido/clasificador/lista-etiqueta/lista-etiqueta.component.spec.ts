import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaEtiquetaComponent } from './lista-etiqueta.component';

describe('ListaEtiquetaComponent', () => {
  let component: ListaEtiquetaComponent;
  let fixture: ComponentFixture<ListaEtiquetaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaEtiquetaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaEtiquetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
