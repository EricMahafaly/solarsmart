import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CommentListComponent} from "./comment-list.component";
import {AvatarModule} from "primeng/avatar";
import {FormsModule} from "@angular/forms";
import {RatingModule} from "../../../../shared/components/rating/rating.module";
import {TruncatePipe} from "../../../../shared/pipe/truncate.pipe";
import {SkeletonModule} from "primeng/skeleton";



@NgModule({
  declarations: [CommentListComponent, TruncatePipe],
  imports: [
    CommonModule,
    AvatarModule,
    RatingModule,
    FormsModule,
    RatingModule,
    SkeletonModule
  ],
  exports: [
    CommentListComponent,
    TruncatePipe
  ]
})
export class CommentListModule { }
