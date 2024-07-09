import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableColInfoComponent } from './table-col-info.component';

describe('TableColInfoComponent', () => {
  let component: TableColInfoComponent;
  let fixture: ComponentFixture<TableColInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TableColInfoComponent]
    });
    fixture = TestBed.createComponent(TableColInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
