import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
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
    startDate: new FormControl(''),
    endDate: new FormControl(''),
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
  yAxisLabelTemperature = 'Temperature';
  yAxisLabelHumidity = 'Humidity';
  autoScale = true;
  temperatureStats: { name: string, series: { name: Date, value: number }[] }[] = [];
  humidityStats: { name: string, series: { name: Date, value: number }[] }[] = [];


  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
  };
  minDate: any;


  constructor(private http: HttpClient, private route: ActivatedRoute, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.uuid = this.route.snapshot.paramMap.get('uuid');

    this.http.get<StatisticEntity[]>("http://localhost:8080/stats/" + this.uuid)
      .subscribe((x: StatisticEntity[]) => this.prepareStats(x));
  }

  prepareStats(statisticEntities: StatisticEntity[]) {
    console.log(statisticEntities)
    let actualTemperature: { name: Date; value: number; }[] = [];
    let expectedTemperature: { name: Date; value: number; }[] = [];
    let deltaTemperature: { name: Date; value: number; }[] = [];

    let actualHumidity: { name: Date; value: number; }[] = [];
    let expectedHumidity: { name: Date; value: number; }[] = [];
    let deltaHumidity: { name: Date; value: number; }[] = [];


    statisticEntities.forEach(function (statisticEntity) {
      actualTemperature.push({name: new Date(statisticEntity.dateTime), value: statisticEntity.actualTemperature})
      expectedTemperature.push({name: new Date(statisticEntity.dateTime), value: statisticEntity.temperature})
      deltaTemperature.push({
        name: new Date(statisticEntity.dateTime),
        value: Math.abs(statisticEntity.temperature - statisticEntity.actualTemperature)
      })

      actualHumidity.push({name: new Date(statisticEntity.dateTime), value: statisticEntity.actualHumidity})
      expectedHumidity.push({name: new Date(statisticEntity.dateTime), value: statisticEntity.humidity})
      deltaHumidity.push({
        name: new Date(statisticEntity.dateTime),
        value: Math.abs(statisticEntity.humidity - statisticEntity.actualHumidity)
      })
    })

    this.temperatureStats = []
    this.temperatureStats.push({name: "Реальна Температура", series: actualTemperature})
    this.temperatureStats.push({name: "Задана температура", series: expectedTemperature})
    this.temperatureStats.push({name: "Дельта", series: deltaTemperature})
    this.temperatureStats = [...this.temperatureStats]

    console.log(this.temperatureStats)

    this.humidityStats = []
    this.humidityStats.push({name: "Реальна вологість", series: actualHumidity})
    this.humidityStats.push({name: "Задана вологість", series: expectedHumidity})
    this.humidityStats.push({name: "Дельта", series: deltaHumidity})
    this.humidityStats = [...this.humidityStats]

    console.log(this.humidityStats)
  }

  submit() {
    console.log("duck")
  }

  dateUpdated() {
    let startDate = '';
    if (this.dateSelectiveForm.get("startDate")?.value) {
      startDate = new Date(this.dateSelectiveForm.get("startDate")?.value).toISOString()
    }

    let endDate = ''
    if (this.dateSelectiveForm.get("endDate")?.value) {
      endDate = new Date(this.dateSelectiveForm.get("endDate")?.value).toISOString()
    }


    let params: HttpParams = new HttpParams().set("startDate", startDate).set("endDate", endDate);

    this.http.get<StatisticEntity[]>("http://localhost:8080/stats/" + this.uuid, {params: params})
      .subscribe((x: StatisticEntity[]) => this.prepareStats(x))


  }
}
