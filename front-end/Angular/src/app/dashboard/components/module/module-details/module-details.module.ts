import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ModuleDetailsComponent} from "./module-details.component";
import {RouterModule} from "@angular/router";
import {NgbCollapse} from "@ng-bootstrap/ng-bootstrap";
import {AdditionalInfoListModule} from "./additional-info-list/additional-info-list.module";



@NgModule({
  declarations: [ModuleDetailsComponent],
    imports: [
        RouterModule,
        CommonModule,
        NgbCollapse,
        RouterModule.forChild([
            {
                path: '',
                component: ModuleDetailsComponent
            }
        ]),
        AdditionalInfoListModule
    ]
})
export class ModuleDetailsModule { }
