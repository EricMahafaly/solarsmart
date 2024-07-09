import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SelectGenericComponent} from "./select-generic.component";
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [SelectGenericComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [SelectGenericComponent]
})
export class SelectGenericModule { }
