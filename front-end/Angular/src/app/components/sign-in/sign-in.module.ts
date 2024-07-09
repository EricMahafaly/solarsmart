import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SignInComponent} from "./sign-in.component";
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import {InputFieldModule} from "../../shared/input-field/input-field.module";
import {DividerModule} from "primeng/divider";
import {PasswordModule} from "primeng/password";
import {SharedModule} from "primeng/api";



@NgModule({
  declarations: [SignInComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    InputFieldModule,
    // RouterModule,
    RouterModule.forChild([
      {
        path: '',
        component: SignInComponent,
      }
    ]),
    DividerModule,
    PasswordModule,
    SharedModule
  ],
  exports: [RouterModule]
})
export class SignInModule { }
