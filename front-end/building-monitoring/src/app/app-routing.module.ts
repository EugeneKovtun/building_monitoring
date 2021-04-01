import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RoomControllerComponent} from "./room-controller/room-controller.component";

const routes: Routes = [
  {path:"room/:id", component:RoomControllerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
