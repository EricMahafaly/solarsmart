import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleDetailComponentComponent } from './single-detail-component.component';

describe('SingleDetailComponentComponent', () => {
  let component: SingleDetailComponentComponent;
  let fixture: ComponentFixture<SingleDetailComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SingleDetailComponentComponent]
    });
    fixture = TestBed.createComponent(SingleDetailComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
