import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SingleDetailComponentComponent } from './single-detail-component.component';
import {ChartStatisticModule} from "../../../shared/components/chart-statistic/chart-statistic.module";
import {DateFilterModule} from "../../../shared/components/date-filter/date-filter.module";
import {SelectGenericModule} from "../../../shared/components/select-generic/select-generic.module";
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";



@NgModule({
  declarations: [
    SingleDetailComponentComponent
  ],
  exports: [
    SingleDetailComponentComponent
  ],
  imports: [
    CommonModule,
    ChartStatisticModule,
    DateFilterModule,
    SelectGenericModule
  ]
})
export class SingleDetailComponentModule { }
