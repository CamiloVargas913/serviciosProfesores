import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarvehiculoComponent } from './agregarvehiculo.component';

describe('AgregarvehiculoComponent', () => {
  let component: AgregarvehiculoComponent;
  let fixture: ComponentFixture<AgregarvehiculoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgregarvehiculoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarvehiculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
