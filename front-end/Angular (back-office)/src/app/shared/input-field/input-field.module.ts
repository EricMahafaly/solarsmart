import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InputValidationDirective} from "./directives/input-validation.directive";



@NgModule({
  declarations: [InputValidationDirective],
  exports: [
    InputValidationDirective
  ],
  imports: [
    CommonModule
  ]
})
export class InputFieldModule { }
