import { NgModule } from "@angular/core";
import { TabMenuComponent } from "./tab-menu.component";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations :  [TabMenuComponent],
  imports : [
    CommonModule,
    RouterModule
  ],
  exports : [TabMenuComponent]
})

export class TabMenuModule{}
