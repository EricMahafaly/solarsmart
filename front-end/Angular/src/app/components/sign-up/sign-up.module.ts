import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignUpComponent } from './sign-up.component';
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputFieldModule} from "../../shared/input-field/input-field.module";
import {ErrorFieldModule} from "../../shared/components/error-field/error-field.module";
import {PasswordModule} from "primeng/password";
import {DividerModule} from "primeng/divider";



@NgModule({
  declarations: [
    SignUpComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    InputFieldModule,
    ErrorFieldModule,
    RouterModule.forChild([
      {
        path: '',
        component: SignUpComponent,
      }
    ]),
    FormsModule,
    PasswordModule,
    DividerModule
  ],
  exports: [RouterModule]
})
export class SignUpModule { }
