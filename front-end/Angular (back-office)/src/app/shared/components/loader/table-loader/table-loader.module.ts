import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableLoaderComponent } from './table-loader.component';
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";



@NgModule({
  declarations: [
    TableLoaderComponent
  ],
  exports: [
    TableLoaderComponent
  ],
  imports: [
    CommonModule,
    NgxSkeletonLoaderModule
  ]
})
export class TableLoaderModule { }
