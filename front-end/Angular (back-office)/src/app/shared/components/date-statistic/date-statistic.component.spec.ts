import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DateStatisticComponent } from './date-statistic.component';

describe('DateStatisticComponent', () => {
  let component: DateStatisticComponent;
  let fixture: ComponentFixture<DateStatisticComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DateStatisticComponent]
    });
    fixture = TestBed.createComponent(DateStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
