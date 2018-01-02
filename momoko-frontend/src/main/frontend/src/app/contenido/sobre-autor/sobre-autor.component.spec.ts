import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SobreAutorComponent } from './sobre-autor.component';

describe('SobreAutorComponent', () => {
  let component: SobreAutorComponent;
  let fixture: ComponentFixture<SobreAutorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SobreAutorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SobreAutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
