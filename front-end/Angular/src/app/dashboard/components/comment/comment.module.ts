import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommentComponent } from './comment.component';
import {AvatarModule} from "primeng/avatar";
import {RouterModule} from "@angular/router";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";
import { CommentListComponent } from './comment-list/comment-list.component';
import {CommentListModule} from "./comment-list/comment-list.module";
import {PaginationModule} from "../../../shared/components/pagination/pagination.module";
import {SelectGenericModule} from "../../../shared/components/select-generic/select-generic.module";
import {ListLoaderModule} from "../../../shared/components/loader/list-loader/list-loader.module";

@NgModule({
  declarations: [
    CommentComponent
  ],
  exports: [
    CommentComponent
  ],
    imports: [
        CommonModule,
        AvatarModule,
        CommentListModule,
        RouterModule.forChild([
            {
              title: 'Comments and Ratings',
                path: '',
                component: CommentComponent
            }
        ]),
        PaginationModule,
        SelectGenericModule,
        ListLoaderModule,
    ]
})
export class CommentModule { }
