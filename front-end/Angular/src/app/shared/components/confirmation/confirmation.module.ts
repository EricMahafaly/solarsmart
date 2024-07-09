import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfirmationComponent } from './confirmation.component';
import {InputFieldModule} from "../../input-field/input-field.module";
import {ModalTitleDirective} from "@coreui/angular";
import {ReactiveFormsModule} from "@angular/forms";
import {NgbModalModule} from "@ng-bootstrap/ng-bootstrap";



@NgModule({
  declarations: [
    ConfirmationComponent
  ],
  imports: [
    CommonModule,
    InputFieldModule,
    NgbModalModule,
    ReactiveFormsModule,
    ModalTitleDirective
  ]
})
export class ConfirmationModule { }
