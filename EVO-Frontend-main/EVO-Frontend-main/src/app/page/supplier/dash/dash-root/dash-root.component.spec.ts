import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashRootComponent } from './dash-root.component';

describe('DashRootComponent', () => {
  let component: DashRootComponent;
  let fixture: ComponentFixture<DashRootComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashRootComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashRootComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});