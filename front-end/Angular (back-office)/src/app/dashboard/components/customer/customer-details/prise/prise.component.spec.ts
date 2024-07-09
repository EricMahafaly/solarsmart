import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriseComponent } from './prise.component';

describe('PriseComponent', () => {
  let component: PriseComponent;
  let fixture: ComponentFixture<PriseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PriseComponent]
    });
    fixture = TestBed.createComponent(PriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
