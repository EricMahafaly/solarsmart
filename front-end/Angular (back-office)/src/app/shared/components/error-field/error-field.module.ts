import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorFieldComponent } from './error-field.component';



@NgModule({
  declarations: [
    ErrorFieldComponent
  ],
  exports: [
    ErrorFieldComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ErrorFieldModule { }
