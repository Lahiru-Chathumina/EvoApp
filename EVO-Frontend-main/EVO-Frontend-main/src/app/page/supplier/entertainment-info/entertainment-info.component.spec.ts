import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntertainmentInfoComponent} from './entertainment-info.component';

describe('EntertainmentInfoComponent', () => {
  let component: EntertainmentInfoComponent;
  let fixture: ComponentFixture<EntertainmentInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EntertainmentInfoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntertainmentInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
