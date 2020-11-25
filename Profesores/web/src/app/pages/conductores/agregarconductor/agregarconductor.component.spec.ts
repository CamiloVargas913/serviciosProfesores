import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarconductorComponent } from './agregarconductor.component';

describe('AgregarconductorComponent', () => {
  let component: AgregarconductorComponent;
  let fixture: ComponentFixture<AgregarconductorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgregarconductorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarconductorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
