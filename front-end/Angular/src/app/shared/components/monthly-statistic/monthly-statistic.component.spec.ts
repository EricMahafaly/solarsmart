import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlyStatisticComponent } from './monthly-statistic.component';

describe('MonthlyStatisticComponent', () => {
  let component: MonthlyStatisticComponent;
  let fixture: ComponentFixture<MonthlyStatisticComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MonthlyStatisticComponent]
    });
    fixture = TestBed.createComponent(MonthlyStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
