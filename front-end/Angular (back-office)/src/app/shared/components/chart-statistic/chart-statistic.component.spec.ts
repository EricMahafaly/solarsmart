import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartStatisticComponent } from './chart-statistic.component';

describe('ChartStatisticComponent', () => {
  let component: ChartStatisticComponent;
  let fixture: ComponentFixture<ChartStatisticComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChartStatisticComponent]
    });
    fixture = TestBed.createComponent(ChartStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
