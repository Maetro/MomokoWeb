import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaUrlsComponent } from './lista-urls.component';

describe('ListaUrlsComponent', () => {
  let component: ListaUrlsComponent;
  let fixture: ComponentFixture<ListaUrlsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaUrlsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaUrlsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
