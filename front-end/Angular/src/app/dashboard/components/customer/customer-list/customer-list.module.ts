import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CustomerListComponent} from "./customer-list.component";
import {RouterModule} from "@angular/router";
import {CdkTableModule} from "@angular/cdk/table";
import {PaginationModule} from "../../../../shared/components/pagination/pagination.module";
import {TableLoaderModule} from "../../../../shared/components/loader/table-loader/table-loader.module";
import {ReactiveFormsModule} from "@angular/forms";
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";
import {AvatarModule} from "primeng/avatar";
import {MatSortModule} from "@angular/material/sort";
import {SkeletonModule} from "primeng/skeleton";



@NgModule({
  declarations: [CustomerListComponent],
    imports: [
        CommonModule,
        RouterModule,
        PaginationModule,
        TableLoaderModule,
        RouterModule.forChild([
            {
                path: '',
                component: CustomerListComponent
            }
        ]),
        ReactiveFormsModule,
        NgxSkeletonLoaderModule,
        AvatarModule,
        MatSortModule,
        SkeletonModule
    ]
})
export class CustomerListModule { }
