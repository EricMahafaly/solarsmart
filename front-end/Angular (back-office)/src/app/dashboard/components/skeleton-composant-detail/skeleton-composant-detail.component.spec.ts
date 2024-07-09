import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkeletonComposantDetailComponent } from './skeleton-composant-detail.component';

describe('SkeletonComposantDetailComponent', () => {
  let component: SkeletonComposantDetailComponent;
  let fixture: ComponentFixture<SkeletonComposantDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SkeletonComposantDetailComponent]
    });
    fixture = TestBed.createComponent(SkeletonComposantDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
