import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitorsAddComponent } from './visitors-add.component';

describe('VisitorsAddComponent', () => {
  let component: VisitorsAddComponent;
  let fixture: ComponentFixture<VisitorsAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisitorsAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitorsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
