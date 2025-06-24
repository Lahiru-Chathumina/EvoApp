import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetTogetherFormComponent } from './get-together-form.component';

describe('GetTogetherFormComponent', () => {
  let component: GetTogetherFormComponent;
  let fixture: ComponentFixture<GetTogetherFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetTogetherFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetTogetherFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});


