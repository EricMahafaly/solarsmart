import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AdditionalInfoListComponent} from "./additional-info-list.component";
import {NgbCollapseModule} from "@ng-bootstrap/ng-bootstrap";



@NgModule({
  declarations: [AdditionalInfoListComponent],
  exports: [
    AdditionalInfoListComponent
  ],
  imports: [
    CommonModule,
    NgbCollapseModule
  ]
})
export class AdditionalInfoListModule { }
