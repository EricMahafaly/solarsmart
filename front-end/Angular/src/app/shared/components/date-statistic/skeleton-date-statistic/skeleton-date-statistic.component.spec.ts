import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkeletonDateStatisticComponent } from './skeleton-date-statistic.component';

describe('SkeletonDateStatisticComponent', () => {
  let component: SkeletonDateStatisticComponent;
  let fixture: ComponentFixture<SkeletonDateStatisticComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SkeletonDateStatisticComponent]
    });
    fixture = TestBed.createComponent(SkeletonDateStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
