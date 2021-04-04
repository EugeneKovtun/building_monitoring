import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RoomControllerComponent} from "./room-controller/room-controller.component";
import {BuildingComponent} from "./building/building.component";

const routes: Routes = [
  {path: "room/:uuid", component: RoomControllerComponent},
  {path: "room", component: BuildingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
