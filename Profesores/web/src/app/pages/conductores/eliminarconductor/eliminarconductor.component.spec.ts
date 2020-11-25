import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarconductorComponent } from './eliminarconductor.component';

describe('EliminarconductorComponent', () => {
  let component: EliminarconductorComponent;
  let fixture: ComponentFixture<EliminarconductorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EliminarconductorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EliminarconductorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
