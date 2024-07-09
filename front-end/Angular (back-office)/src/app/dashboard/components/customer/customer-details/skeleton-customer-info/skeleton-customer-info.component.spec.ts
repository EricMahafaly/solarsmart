import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkeletonCustomerInfoComponent } from './skeleton-customer-info.component';

describe('SkeletonCustomerInfoComponent', () => {
  let component: SkeletonCustomerInfoComponent;
  let fixture: ComponentFixture<SkeletonCustomerInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SkeletonCustomerInfoComponent]
    });
    fixture = TestBed.createComponent(SkeletonCustomerInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
