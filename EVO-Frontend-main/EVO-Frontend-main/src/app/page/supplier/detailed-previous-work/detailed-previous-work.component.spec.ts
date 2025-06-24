import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedPreviousWorkComponent } from './detailed-previous-work.component';

describe('DetailedPreviousWorkComponent', () => {
  let component: DetailedPreviousWorkComponent;
  let fixture: ComponentFixture<DetailedPreviousWorkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailedPreviousWorkComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailedPreviousWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
