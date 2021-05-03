import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RoomControllerComponent} from './room-controller/room-controller.component';
import {HttpClientModule} from "@angular/common/http";
import {MatSliderModule} from "@angular/material/slider";
import {MatButtonModule} from "@angular/material/button";
import {BuildingComponent} from './building/building.component';
import {StatisticComponent} from './statistic/statistic.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatInputModule} from "@angular/material/input";
import {MatNativeDateModule} from "@angular/material/core";
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {ReactiveFormsModule} from "@angular/forms";
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import {NgxChartsModule} from "@swimlane/ngx-charts";

@NgModule({
    declarations: [
        AppComponent,
        RoomControllerComponent,
        BuildingComponent,
        StatisticComponent,
        AdminPanelComponent
    ],
    imports: [
        BrowserModule,
        MatSliderModule,
        HttpClientModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatButtonModule,
        MatDatepickerModule,
        MatInputModule,
        MatNativeDateModule,
        MatIconModule,
        MatListModule,
        ReactiveFormsModule,
        NgxChartsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
