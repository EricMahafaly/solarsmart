import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TableColumnInfoType} from "../../../../models/core/table/table-column-info-type";
import {TableColInfoComponent} from "./table-col-info/table-col-info.component";
import {RouterModule} from "@angular/router";



@NgModule({
  declarations: [TableColInfoComponent],
  imports: [
    CommonModule,
    RouterModule
  ]
})
export class DynamicTableColModule { }
