import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComponentDetailComponent } from './component-detail.component';
import {NgChartsModule} from "ng2-charts";
import {SelectGenericModule} from "../../../shared/components/select-generic/select-generic.module";
import {SkeletonComposantDetailModule} from "../skeleton-composant-detail/skeleton-composant-detail.module";
import {DateFilterModule} from "../../../shared/components/date-filter/date-filter.module";



@NgModule({
  declarations: [
    ComponentDetailComponent
  ],
  exports: [
    ComponentDetailComponent
  ],
  imports: [
    CommonModule,
    NgChartsModule,
    SelectGenericModule,
    SkeletonComposantDetailModule,
    DateFilterModule
  ]
})
export class ComponentDetailModule { }
