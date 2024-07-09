import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DateStatisticComponent} from "./date-statistic.component";
import {ChartStatisticModule} from "../chart-statistic/chart-statistic.module";
import { SkeletonDateStatisticComponent } from './skeleton-date-statistic/skeleton-date-statistic.component';
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";



@NgModule({
  declarations: [DateStatisticComponent, SkeletonDateStatisticComponent],
  exports: [
    DateStatisticComponent,
    SkeletonDateStatisticComponent
  ],
    imports: [
        CommonModule,
        ChartStatisticModule,
        NgxSkeletonLoaderModule
    ]
})
export class DateStatisticModule { }
