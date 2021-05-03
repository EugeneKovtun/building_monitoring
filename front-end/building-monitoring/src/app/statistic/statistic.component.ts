import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {StatisticEntity} from "./statisticEntity";
import {DataItem} from "@swimlane/ngx-charts";

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
  view: [number, number] = [700, 400];

  showXAxis = true;
  showYAxis = true;
  timeline = false;
  gradient = true;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Time';
  showYAxisLabel = true;
  yAxisLabel = 'Temperature';
  autoScale = true;
  temperatureStats: { name: string, series: { name: Date, value: number }[] }[] = [];


  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
  };


  constructor(private http: HttpClient, private route: ActivatedRoute, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.uuid = this.route.snapshot.paramMap.get('uuid');

    this.http.get<StatisticEntity[]>("http://localhost:8080/stats/" + this.uuid)
      .subscribe((x: StatisticEntity[]) => this.prepareStats(x));


    // this.temperatureStats.push({
    //   "name": "Задана Температура",
    //     "series": [
    //     {
    //       "name":  new Date("2021-05-03T01:34:28.25627"),
    //       "value": 21
    //     },
    //     {
    //       "name": new Date("2021-05-03T01:34:55.19915"),
    //       "value": 22
    //     },
    //     {
    //       "name": new Date("2021-05-03T01:35:12.912218"),
    //       "value": 22
    //     },
    //     {
    //       "name": new Date("2021-05-03T01:35:21.259734"),
    //       "value": 23
    //     },
    //     {
    //       "name": new Date("2021-05-03T01:35:29.857712"),
    //       "value": 22
    //     },
    //     {
    //       "name": new Date("2021-05-03T01:46:44.422806"),
    //       "value": 25
    //     }
    //   ]
    // })
    console.log(this.temperatureStats)
  }

  prepareStats(statsEntity: StatisticEntity[]) {
    console.log(statsEntity)
    let actualTemperature: { name: Date; value: number; }[] = [];
    statsEntity.forEach(function (value) {
      actualTemperature.push({name: new Date(value.dateTime), value: value.actualTemperature})
    })

    this.temperatureStats.push({name: "Реальна Температура", series: actualTemperature})

    let expectedTemperature: { name: Date; value: number; }[] = [];

    statsEntity.forEach(function (value){
      expectedTemperature.push({name: new Date(value.dateTime), value: value.temperature})
    })

    this.temperatureStats.push({name: "Задана температура", series: expectedTemperature})


  }

  submit() {
    console.log("duck")
  }
}
