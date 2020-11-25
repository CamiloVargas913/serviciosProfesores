import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Not401Component } from './not401.component';

describe('Not401Component', () => {
  let component: Not401Component;
  let fixture: ComponentFixture<Not401Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Not401Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Not401Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
