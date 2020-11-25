import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AsociarautorlectorComponent } from './asociarautorlector.component';

describe('AsociarautorlectorComponent', () => {
  let component: AsociarautorlectorComponent;
  let fixture: ComponentFixture<AsociarautorlectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AsociarautorlectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AsociarautorlectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
