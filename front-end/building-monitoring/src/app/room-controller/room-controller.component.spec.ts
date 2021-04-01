import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomControllerComponent } from './room-controller.component';

describe('RoomControllerComponent', () => {
  let component: RoomControllerComponent;
  let fixture: ComponentFixture<RoomControllerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoomControllerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomControllerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
