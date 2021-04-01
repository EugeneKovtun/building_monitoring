import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-room-controller',
  templateUrl: './room-controller.component.html',
  styleUrls: ['./room-controller.component.css']
})
export class RoomControllerComponent implements OnInit {

  id:string|null = null;

  constructor(
    private route: ActivatedRoute,

  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
  }

}
