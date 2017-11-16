import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VideosHorizontalComponent } from './videos-horizontal.component';

describe('VideosHorizontalComponent', () => {
  let component: VideosHorizontalComponent;
  let fixture: ComponentFixture<VideosHorizontalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VideosHorizontalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VideosHorizontalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
