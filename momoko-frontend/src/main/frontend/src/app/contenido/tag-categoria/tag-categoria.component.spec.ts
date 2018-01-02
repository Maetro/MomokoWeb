import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TagCategoriaComponent } from './tag-categoria.component';

describe('TagCategoriaComponent', () => {
  let component: TagCategoriaComponent;
  let fixture: ComponentFixture<TagCategoriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TagCategoriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TagCategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
