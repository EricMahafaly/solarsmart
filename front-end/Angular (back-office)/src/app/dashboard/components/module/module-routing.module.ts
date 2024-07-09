import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {
    path: '',
    title:'Modules',
    loadChildren: () =>
      import('./module-list/module-list.module').then(m => m.ModuleListModule)
  },
  {
    path: 'new',
    title: 'Create module',
    loadChildren: () =>
      import('./module-creation/module-creation.module').then(m => m.ModuleCreationModule)
  },
  {
    path: ':id/edit',
    title: 'Edit module',
    loadChildren: () =>
      import('./module-creation/module-creation.module').then(m => m.ModuleCreationModule)
  },
  {
    path: ':id/details',
    title: 'Module detail',
    loadChildren: () =>
      import('./module-details/module-details.module').then(m => m.ModuleDetailsModule)
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
export class ModuleRoutingModule { }
