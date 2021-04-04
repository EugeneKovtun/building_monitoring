import {Component, OnInit} from '@angular/core';
import {Room} from "../room-controller/room";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-building',
  templateUrl: './building.component.html',
  styleUrls: ['./building.component.css']
})
export class BuildingComponent implements OnInit {

  rooms!: Room[];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get<Room[]>("http://localhost:8080/zone/")
      .subscribe((rooms: Room[]) => this.rooms = rooms)
  }

}
