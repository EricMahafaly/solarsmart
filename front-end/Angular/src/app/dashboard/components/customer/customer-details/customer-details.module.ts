import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {CustomerDetailsComponent} from "./customer-details.component";
import {RouterModule} from "@angular/router";
import { PanelComponent } from './panel/panel.component';
import { PriseComponent } from './prise/prise.component';
import {TabMenuModule} from "../../../../shared/components/tab-menu/tab-menu.module";
import {SelectGenericModule} from "../../../../shared/components/select-generic/select-generic.module";
import {WeeklyStatisticModule} from "../../../../shared/components/weekly-statistic/weekly-statistic.module";
import {MonthlyStatisticModule} from "../../../../shared/components/monthly-statistic/monthly-statistic.module";
import {CardModule, GridModule, PlaceholderModule, UtilitiesModule} from "@coreui/angular";
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";
import { SkeletonCustomerInfoComponent } from './skeleton-customer-info/skeleton-customer-info.component';
import {DateStatisticModule} from "../../../../shared/components/date-statistic/date-statistic.module";
import {ComponentDetailModule} from "../../component-detail/component-detail.module";
import {SingleDetailComponentModule} from "../../single-detail-component/single-detail-component.module";
import {SkeletonComposantDetailModule} from "../../skeleton-composant-detail/skeleton-composant-detail.module";
import {AvatarModule} from "primeng/avatar";
import {ButtonModule} from "primeng/button";



@NgModule({
  declarations: [CustomerDetailsComponent, PanelComponent, SkeletonCustomerInfoComponent],
    imports: [
        CommonModule,
        TabMenuModule,
        SelectGenericModule,
        WeeklyStatisticModule,
        MonthlyStatisticModule,
        PlaceholderModule,
        GridModule,
        UtilitiesModule,
        RouterModule.forChild([
            {
                path: '',
                component: CustomerDetailsComponent,
                children: [
                    {
                        path: 'batteries/:id',
                        title: 'Battery details',
                        loadChildren: () => import('./battery/battery.module').then(m => m.BatteryModule)
                    },
                    {
                      path: 'panels/:id',
                      title: 'Panel details',
                      component: PanelComponent
                    },
                    {
                        path: 'prises/:id',
                        title: 'Prise details',
                        loadChildren: () => import('./prise/prise.module').then(m => m.PriseModule)
                    }
                ],
            }
        ]),
        NgOptimizedImage,
        NgxSkeletonLoaderModule,
        DateStatisticModule,
        ComponentDetailModule,
        SingleDetailComponentModule,
        SkeletonComposantDetailModule,
        AvatarModule,
        ButtonModule
    ],
  exports: [RouterModule]
})
export class CustomerDetailsModule { }
