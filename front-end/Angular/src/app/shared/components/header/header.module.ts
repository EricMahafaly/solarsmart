import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {HeaderComponent} from "./header.component";
import {RouterModule} from "@angular/router";
import {DropdownModule} from "../dropdown/dropdown.module";
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";
import {AvatarModule} from "primeng/avatar";



@NgModule({
  declarations: [HeaderComponent],
  exports: [
    HeaderComponent
  ],
    imports: [
        RouterModule,
        CommonModule,
        NgOptimizedImage,
        DropdownModule,
        NgxSkeletonLoaderModule,
        AvatarModule
    ]
})
export class HeaderModule { }
