import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListLoaderComponent } from './list-loader.component';
import {SkeletonModule} from "primeng/skeleton";



@NgModule({
  declarations: [
    ListLoaderComponent
  ],
  exports: [
    ListLoaderComponent
  ],
  imports: [
    CommonModule,
    SkeletonModule
  ]
})
export class ListLoaderModule { }
