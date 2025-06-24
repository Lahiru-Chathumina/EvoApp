import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPreviousComponent } from './add-previous.component';

describe('AddPreviousComponent', () => {
  let component: AddPreviousComponent;
  let fixture: ComponentFixture<AddPreviousComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddPreviousComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPreviousComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
