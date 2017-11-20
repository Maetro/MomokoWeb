import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaGeneroComponent } from './lista-genero.component';

describe('ListaGeneroComponent', () => {
  let component: ListaGeneroComponent;
  let fixture: ComponentFixture<ListaGeneroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaGeneroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaGeneroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
