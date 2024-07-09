import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BatteryComponent} from "./battery.component";
import {NgCircleProgressModule} from "ng-circle-progress";
import {SelectGenericModule} from "../../../../../shared/components/select-generic/select-generic.module";
import {WeeklyStatisticModule} from "../../../../../shared/components/weekly-statistic/weekly-statistic.module";
import {MonthlyStatisticModule} from "../../../../../shared/components/monthly-statistic/monthly-statistic.module";
import {Router, RouterModule} from "@angular/router";
import {ChartStatisticModule} from "../../../../../shared/components/chart-statistic/chart-statistic.module";
import {LoaderModule} from "../../../../../shared/components/loader/loader.module";
import {ColDirective, PlaceholderModule} from "@coreui/angular";
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";
import {DateStatisticModule} from "../../../../../shared/components/date-statistic/date-statistic.module";
import {ComponentDetailModule} from "../../../component-detail/component-detail.module";
import {SingleDetailComponentModule} from "../../../single-detail-component/single-detail-component.module";
import {SkeletonComposantDetailModule} from "../../../skeleton-composant-detail/skeleton-composant-detail.module";
import {ButtonModule} from "primeng/button";



@NgModule({
  declarations: [BatteryComponent],
    imports: [
        CommonModule,
        NgCircleProgressModule,
        SelectGenericModule,
        WeeklyStatisticModule,
        MonthlyStatisticModule,
        ChartStatisticModule,
        SingleDetailComponentModule,
        PlaceholderModule,
        RouterModule.forChild([
            {
                path: '',
                component: BatteryComponent
            }
        ]),
        LoaderModule,
        ColDirective,
        NgxSkeletonLoaderModule,
        DateStatisticModule,
        ComponentDetailModule,
        SingleDetailComponentModule,
        SkeletonComposantDetailModule,
        ButtonModule
    ]
})
export class BatteryModule { }
