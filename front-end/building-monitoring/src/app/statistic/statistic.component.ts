import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})
export class StatisticComponent implements OnInit {
  dateSelectiveForm = this.formBuilder.group({
    dateFrom: '',
    dateTo: ''
  });
  private uuid?: string | null;


  constructor(private http: HttpClient, private route: ActivatedRoute, private formBuilder: FormBuilder) {
    // this.dateSelectiveForm = new FormGroup({dateFrom: new FormControl()})
  }

  ngOnInit(): void {
    this.uuid = this.route.snapshot.paramMap.get('uuid');

    this.http.get("http://localhost:8080/stats/" + this.uuid)
      .subscribe((x: any) => console.log(x));
  }

  submit() {
    console.log("duck")
  }
}
