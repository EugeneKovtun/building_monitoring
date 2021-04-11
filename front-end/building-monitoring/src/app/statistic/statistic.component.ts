import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

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


    constructor(private http: HttpClient, private formBuilder: FormBuilder) {
        // this.dateSelectiveForm = new FormGroup({dateFrom: new FormControl()})
    }

    ngOnInit(): void {
    }

    submit() {
        console.log("duck")
    }
}
