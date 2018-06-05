import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificadorUrlsComponent } from './modificador-urls.component';

describe('ModificadorUrlsComponent', () => {
  let component: ModificadorUrlsComponent;
  let fixture: ComponentFixture<ModificadorUrlsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModificadorUrlsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificadorUrlsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
