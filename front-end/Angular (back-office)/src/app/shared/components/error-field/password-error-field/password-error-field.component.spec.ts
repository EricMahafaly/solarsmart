import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordErrorFieldComponent } from './password-error-field.component';

describe('PasswordErrorFieldComponent', () => {
  let component: PasswordErrorFieldComponent;
  let fixture: ComponentFixture<PasswordErrorFieldComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PasswordErrorFieldComponent]
    });
    fixture = TestBed.createComponent(PasswordErrorFieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
