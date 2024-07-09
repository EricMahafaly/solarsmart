import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {WeeklyStatisticComponent} from "./weekly-statistic.component";
import {NgChartsModule } from 'ng2-charts';
import {ChartStatisticModule} from "../chart-statistic/chart-statistic.module";
import {DateStatisticModule} from "../date-statistic/date-statistic.module";
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";
import {WeeklyStatisticSkeletonComponent} from "./weekly-statistic-skeleton/weekly-statistic-skeleton.component";

@NgModule({
  declarations: [WeeklyStatisticComponent, WeeklyStatisticSkeletonComponent],
    imports: [
        CommonModule,
        NgChartsModule,
        ChartStatisticModule,
        DateStatisticModule,
        NgxSkeletonLoaderModule
    ],

  exports: [WeeklyStatisticComponent, WeeklyStatisticSkeletonComponent]
})
export class WeeklyStatisticModule { }
