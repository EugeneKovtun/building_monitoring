import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RoomControllerComponent} from "./room-controller/room-controller.component";
import {BuildingComponent} from "./building/building.component";
import {StatisticComponent} from "./statistic/statistic.component";
import {AdminPanelComponent} from "./admin-panel/admin-panel.component";

const routes: Routes = [
    {path: '', redirectTo: '/room', pathMatch: 'full'},
    {path: "room/:uuid", component: RoomControllerComponent},
    {path: "room", component: BuildingComponent},
    {path: 'stats/:uuid', component: StatisticComponent},
    {path: 'admin', component: AdminPanelComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
