import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarautorComponent } from './agregarautor.component';

describe('AgregarautorComponent', () => {
  let component: AgregarautorComponent;
  let fixture: ComponentFixture<AgregarautorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgregarautorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarautorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
