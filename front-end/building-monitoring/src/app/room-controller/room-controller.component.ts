import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Room} from "./room";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-room-controller',
  templateUrl: './room-controller.component.html',
  styleUrls: ['./room-controller.component.css']
})
export class RoomControllerComponent implements OnInit {

  uuid: string | null = null;
  room!: Room;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient
  ) {
  }

  ngOnInit(): void {
    this.uuid = this.route.snapshot.paramMap.get('uuid');
    this.http.get<Room>("http://localhost:8080/zone/" + this.uuid)
      .subscribe((room: Room) => {
        this.room = room
      });
  }

  decreaseTemperature() {
    this.room.temperature--;
    this.http.put("http://localhost:8080/zone/" + this.uuid, this.room).subscribe()
  }

  increaseTemperature() {
    this.room.temperature++;
    this.http.put("http://localhost:8080/zone/" + this.uuid, this.room).subscribe()
  }

  decreaseHumidity() {
    this.room.humidity--;
    this.http.put("http://localhost:8080/zone/" + this.uuid, this.room).subscribe()
  }

  increaseHumidity() {
    this.room.humidity++;
    this.http.put("http://localhost:8080/zone/" + this.uuid, this.room).subscribe()

  }
}
