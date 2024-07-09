import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupportSidebarComponent } from './support-sidebar.component';

describe('SupportSidebarComponent', () => {
  let component: SupportSidebarComponent;
  let fixture: ComponentFixture<SupportSidebarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SupportSidebarComponent]
    });
    fixture = TestBed.createComponent(SupportSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
