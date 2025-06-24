import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhotographerInfoComponent} from './photographer-info.component';

describe('PhotographerInfoComponent', () => {
  let component: PhotographerInfoComponent;
  let fixture: ComponentFixture<PhotographerInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PhotographerInfoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PhotographerInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
