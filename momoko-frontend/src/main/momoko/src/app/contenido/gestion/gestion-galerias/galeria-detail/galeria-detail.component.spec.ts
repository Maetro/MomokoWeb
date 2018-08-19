import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GaleriaDetailComponent } from './galeria-detail.component';

describe('GaleriaDetailComponent', () => {
  let component: GaleriaDetailComponent;
  let fixture: ComponentFixture<GaleriaDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GaleriaDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GaleriaDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
