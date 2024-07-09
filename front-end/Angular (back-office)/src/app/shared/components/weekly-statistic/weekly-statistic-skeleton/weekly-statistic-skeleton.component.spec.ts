import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeeklyStatisticSkeletonComponent } from './weekly-statistic-skeleton.component';

describe('WeeklyStatisticSkeletonComponent', () => {
  let component: WeeklyStatisticSkeletonComponent;
  let fixture: ComponentFixture<WeeklyStatisticSkeletonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WeeklyStatisticSkeletonComponent]
    });
    fixture = TestBed.createComponent(WeeklyStatisticSkeletonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
