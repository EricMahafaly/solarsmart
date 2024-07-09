import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HomeComponent} from "./home.component";
import {RouterModule} from "@angular/router";
import {LoaderModule} from "../../../shared/components/loader/loader.module";
import {MatButtonModule} from "@angular/material/button";
import {ChartModule} from "primeng/chart";
import {CommentListModule} from "../comment/comment-list/comment-list.module";
import {RatingModule} from "../../../shared/components/rating/rating.module";
import {SkeletonModule} from "primeng/skeleton";
import {ListLoaderModule} from "../../../shared/components/loader/list-loader/list-loader.module";

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {
        title: 'Home',
        path: '',
        component: HomeComponent,
      },
    ]),
    LoaderModule,
    MatButtonModule,
    ChartModule,
    CommentListModule,
    RatingModule,
    RatingModule,
    SkeletonModule,
    ListLoaderModule
  ]
})
export class HomeModule { }
