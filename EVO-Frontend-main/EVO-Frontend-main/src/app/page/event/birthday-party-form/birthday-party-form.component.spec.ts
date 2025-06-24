import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BirthdayPartyFormComponent } from './birthday-party-form.component';

describe('BirthdayPartyFormComponent', () => {
  let component: BirthdayPartyFormComponent;
  let fixture: ComponentFixture<BirthdayPartyFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BirthdayPartyFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BirthdayPartyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
