import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SkeletonComposantDetailComponent} from "./skeleton-composant-detail.component";
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";
import {DateStatisticModule} from "../../../shared/components/date-statistic/date-statistic.module";



@NgModule({
  declarations: [SkeletonComposantDetailComponent],
  exports: [
    SkeletonComposantDetailComponent
  ],
  imports: [
    CommonModule,
    NgxSkeletonLoaderModule,
    DateStatisticModule
  ]
})
export class SkeletonComposantDetailModule { }
