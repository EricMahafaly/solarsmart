import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ModuleListComponent} from "./module-list.component";
import {RouterModule} from "@angular/router";
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";
import {PaginationModule} from "../../../../shared/components/pagination/pagination.module";
import {ReactiveFormsModule} from "@angular/forms";
import {NgbAccordionModule, NgbCollapse} from "@ng-bootstrap/ng-bootstrap";
import {InputFieldModule} from "../../../../shared/input-field/input-field.module";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {TableModule} from "primeng/table";
import {SkeletonModule} from "primeng/skeleton";
import {ButtonModule} from "primeng/button";
import {PaginatorModule} from "primeng/paginator";
import {AvatarModule} from "primeng/avatar";
import {MatSortModule} from "@angular/material/sort";



@NgModule({
  declarations: [ModuleListComponent],
    imports: [
        CommonModule,
        RouterModule,
        RouterModule.forChild([
            {
                path: '',
                component: ModuleListComponent
            }
        ]),
        NgxSkeletonLoaderModule,
        PaginationModule,
        ReactiveFormsModule,
        NgbAccordionModule,
        NgbCollapse,
        InputFieldModule,
        MatDatepickerModule,
        MatFormFieldModule,
        MatNativeDateModule,
        MatSlideToggleModule,
        TableModule,
        SkeletonModule,
        ButtonModule,
        PaginatorModule,
        AvatarModule,
        MatSortModule,
    ],
  exports: [
    ModuleListComponent
  ]
})
export class ModuleListModule { }
