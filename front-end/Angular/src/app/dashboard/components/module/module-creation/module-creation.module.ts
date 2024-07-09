import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModuleCreationComponent } from './module-creation.component';
import {RouterModule} from "@angular/router";
import {SelectGenericModule} from "../../../../shared/components/select-generic/select-generic.module";
import {ReactiveFormsModule} from "@angular/forms";
import {InputFieldModule} from "../../../../shared/input-field/input-field.module";
import {InputNumberModule} from "primeng/inputnumber";
import {InputTextareaModule} from "primeng/inputtextarea";
import { AdditionalInfoComponent } from './additional-info/additional-info.component';
import { AdditionalInfoListComponent } from '../module-details/additional-info-list/additional-info-list.component';
import {AdditionalInfoListModule} from "../module-details/additional-info-list/additional-info-list.module";



@NgModule({
  declarations: [
    ModuleCreationComponent,
    AdditionalInfoComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    RouterModule.forChild([
      {
        path: '',
        component: ModuleCreationComponent
      }
    ]),
    SelectGenericModule,
    InputFieldModule,
    InputNumberModule,
    InputTextareaModule,
    AdditionalInfoListModule
  ]
})
export class ModuleCreationModule { }
