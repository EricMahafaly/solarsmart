import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MonthlyStatisticComponent} from "./monthly-statistic.component";
import {ChartStatisticModule} from "../chart-statistic/chart-statistic.module";
import {DateStatisticModule} from "../date-statistic/date-statistic.module";
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";



@NgModule({
  declarations: [MonthlyStatisticComponent],
    imports: [
        CommonModule,
        ChartStatisticModule,
        DateStatisticModule,
        NgxSkeletonLoaderModule
    ],
  exports: [
    MonthlyStatisticComponent
  ]
})
export class MonthlyStatisticModule { }
