import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ChartStatisticComponent} from "./chart-statistic.component";
import {NgChartsModule} from "ng2-charts";



@NgModule({
  declarations: [ChartStatisticComponent],
  imports: [
    CommonModule,
    NgChartsModule
  ],
  exports: [ChartStatisticComponent]
})
export class ChartStatisticModule { }
