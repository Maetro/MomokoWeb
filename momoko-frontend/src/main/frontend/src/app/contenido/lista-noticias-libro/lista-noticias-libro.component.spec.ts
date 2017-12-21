import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaNoticiasLibroComponent } from './lista-noticias-libro.component';

describe('ListaNoticiasLibroComponent', () => {
  let component: ListaNoticiasLibroComponent;
  let fixture: ComponentFixture<ListaNoticiasLibroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaNoticiasLibroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaNoticiasLibroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
