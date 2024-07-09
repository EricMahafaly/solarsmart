import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoaderComponent} from "./loader.component";
import { SkeletonLoaderComponent } from './skeleton-loader/skeleton-loader.component';
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";



@NgModule({
  declarations: [LoaderComponent, SkeletonLoaderComponent],
  exports: [
    LoaderComponent,
    SkeletonLoaderComponent
  ],
  imports: [
    CommonModule,
    NgxSkeletonLoaderModule
  ]
})
export class LoaderModule { }
