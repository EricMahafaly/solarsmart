import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PriseComponent} from "./prise.component";
import {RouterModule} from "@angular/router";
import {SelectGenericModule} from "../../../../../shared/components/select-generic/select-generic.module";
import {WeeklyStatisticModule} from "../../../../../shared/components/weekly-statistic/weekly-statistic.module";
import {MonthlyStatisticModule} from "../../../../../shared/components/monthly-statistic/monthly-statistic.module";
import {DateStatisticModule} from "../../../../../shared/components/date-statistic/date-statistic.module";
import {ComponentDetailModule} from "../../../component-detail/component-detail.module";
import {SingleDetailComponentModule} from "../../../single-detail-component/single-detail-component.module";
import {SkeletonComposantDetailModule} from "../../../skeleton-composant-detail/skeleton-composant-detail.module";
import {ButtonModule} from "primeng/button";



@NgModule({
  declarations: [PriseComponent],
    imports: [
        CommonModule,
        SelectGenericModule,
        WeeklyStatisticModule,
        MonthlyStatisticModule,
        RouterModule.forChild([
            {
                path: '',
                component: PriseComponent
            }
        ]),
        DateStatisticModule,
        ComponentDetailModule,
        SingleDetailComponentModule,
        SkeletonComposantDetailModule,
        ButtonModule
    ]
})
export class PriseModule { }
