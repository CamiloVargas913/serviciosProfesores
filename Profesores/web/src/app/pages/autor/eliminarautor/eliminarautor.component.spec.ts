import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarautorComponent } from './eliminarautor.component';

describe('EliminarautorComponent', () => {
  let component: EliminarautorComponent;
  let fixture: ComponentFixture<EliminarautorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EliminarautorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EliminarautorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
