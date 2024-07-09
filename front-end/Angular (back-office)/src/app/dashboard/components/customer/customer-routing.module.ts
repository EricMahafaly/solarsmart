import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";



const routes: Routes = [
  {
    title: 'Clients',
    path: '',
    loadChildren:() =>
      import('./customer-list/customer-list.module').then(m => m.CustomerListModule)
  },
  {
    title: 'Customer detail',
    path: 'details/:id',
    loadChildren:() =>
      import('./customer-details/customer-details.module').then(m => m.CustomerDetailsModule)
  },
  {
    path: 'list',
    redirectTo: '',
    pathMatch: "full"
  }
]


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class CustomerRoutingModule { }
