import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

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

  constructor( private http: HttpClient) {
  }

  ngOnInit(): void {
  }

  submit() {
    this.http.post("http://localhost:8080/zone", this.roomCreationForm.value).subscribe()
    this.roomCreationForm.reset()
    console.log(this.roomCreationForm.value)
  }
}
