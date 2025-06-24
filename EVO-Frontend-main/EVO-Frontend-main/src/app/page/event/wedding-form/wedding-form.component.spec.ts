import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeddingFormComponent } from './wedding-form.component';

describe('WeddingFormComponent', () => {
  let component: WeddingFormComponent;
  let fixture: ComponentFixture<WeddingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WeddingFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WeddingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
