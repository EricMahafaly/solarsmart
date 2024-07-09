import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupportConversationComponent } from './support-conversation.component';

describe('SupportConversationComponent', () => {
  let component: SupportConversationComponent;
  let fixture: ComponentFixture<SupportConversationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SupportConversationComponent]
    });
    fixture = TestBed.createComponent(SupportConversationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
