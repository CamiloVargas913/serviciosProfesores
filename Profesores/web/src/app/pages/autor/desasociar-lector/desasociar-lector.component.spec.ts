import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DesasociarLectorComponent } from './desasociar-lector.component';

describe('DesasociarLectorComponent', () => {
  let component: DesasociarLectorComponent;
  let fixture: ComponentFixture<DesasociarLectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DesasociarLectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DesasociarLectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
