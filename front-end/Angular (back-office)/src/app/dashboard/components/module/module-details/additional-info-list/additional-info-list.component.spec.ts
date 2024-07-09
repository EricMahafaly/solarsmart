import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdditionalInfoListComponent } from './additional-info-list.component';

describe('AdditionalInfoListComponent', () => {
  let component: AdditionalInfoListComponent;
  let fixture: ComponentFixture<AdditionalInfoListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdditionalInfoListComponent]
    });
    fixture = TestBed.createComponent(AdditionalInfoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
