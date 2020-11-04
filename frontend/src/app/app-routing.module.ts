import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {RegistrationComponent} from "./components/registration/registration.component";
import {HomeComponent} from "./components/home/home.component";

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'registration', component:RegistrationComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule{}
export const routingComponents = [RegistrationComponent]
