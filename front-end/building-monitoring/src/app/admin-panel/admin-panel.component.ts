import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Room} from "../room-controller/room";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {
  roomCreationForm = new FormGroup({
    name: new FormControl(''),
    clientId: new FormControl(''),
    humidity: new FormControl(''),
    temperature: new FormControl('')
  });
  rooms!: Room[];

  constructor(private http: HttpClient, private route: Router) {
  }

  ngOnInit(): void {
    this.getRooms();
  }

  submit() {
    this.http.post("http://localhost:8080/zone", this.roomCreationForm.value).subscribe(
      () => this.getRooms()
    )
    this.roomCreationForm.reset()
  }

  navigate(room: Room) {
    this.route.navigate(["/stats", room.uuid])
  }

  private getRooms() {
    this.http.get<Room[]>("http://localhost:8080/zone/")
      .subscribe((rooms: Room[]) => this.rooms = rooms.sort())
  }

  deleteZone(room: Room) {
    this.http.delete("http://localhost:8080/zone/" + room.uuid)
      .subscribe(() => this.getRooms())
  }

  openQR(room: Room) {
    window.open("http://localhost:8080/zone/" + room.uuid + "/qr")

  }
}
