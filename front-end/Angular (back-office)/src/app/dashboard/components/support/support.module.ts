import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SupportListComponent } from './support-list/support-list.component';
import { SupportConversationComponent } from './support-conversation/support-conversation.component';
import {RouterModule} from "@angular/router";
import {MatMenuModule} from "@angular/material/menu";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatButtonModule} from "@angular/material/button";
import {AvatarModule} from "primeng/avatar";
import {MatSidenavModule} from "@angular/material/sidenav";
import {InputTextareaModule} from "primeng/inputtextarea";
import {PaginationModule} from "../../../shared/components/pagination/pagination.module";
import { SupportSidebarComponent } from './support-sidebar/support-sidebar.component';
import {SidenavModule} from "../../../shared/components/sidenav/sidenav.module";
import {NgbCollapse} from "@ng-bootstrap/ng-bootstrap";
import {CommentListModule} from "../comment/comment-list/comment-list.module";
import {TreeSelectModule} from "primeng/treeselect";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoaderModule} from "../../../shared/components/loader/loader.module";
import {ListLoaderModule} from "../../../shared/components/loader/list-loader/list-loader.module";



@NgModule({
  declarations: [
    SupportListComponent,
    SupportConversationComponent,
    SupportSidebarComponent
  ],
    imports: [
        CommonModule,
        RouterModule.forChild([
            {
              title: 'Supports',
                path: '',
                component: SupportListComponent
            }
        ]),
        MatMenuModule,
        MatIconModule,
        MatFormFieldModule,
        MatButtonModule,
        AvatarModule,
        MatSidenavModule,
        InputTextareaModule,
        PaginationModule,
        SidenavModule,
        NgbCollapse,
        CommentListModule,
        TreeSelectModule,
        FormsModule,
        LoaderModule,
        ReactiveFormsModule,
        ListLoaderModule
    ]
})
export class SupportModule { }
