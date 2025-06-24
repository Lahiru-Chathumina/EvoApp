import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BeautyEssentialsInfoComponent } from './beauty-essentials-info.component';

describe('BeautyEssentialsInfoComponent', () => {
  let component: BeautyEssentialsInfoComponent;
  let fixture: ComponentFixture<BeautyEssentialsInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BeautyEssentialsInfoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BeautyEssentialsInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
