import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnivesaryFormComponent } from './annivesary-form.component';

describe('AnnivesaryFormComponent', () => {
  let component: AnnivesaryFormComponent;
  let fixture: ComponentFixture<AnnivesaryFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnnivesaryFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnnivesaryFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
