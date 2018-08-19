import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaGaleriasComponent } from './lista-galerias.component';

describe('ListaGaleriasComponent', () => {
  let component: ListaGaleriasComponent;
  let fixture: ComponentFixture<ListaGaleriasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaGaleriasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaGaleriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
