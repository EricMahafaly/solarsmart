import {Input, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DateFilterComponent} from "./date-filter.component";
import {FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CalendarModule} from "primeng/calendar";
import {DialogModule} from "primeng/dialog";
import {OverlayPanelModule} from "primeng/overlaypanel";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ConfirmationService} from "primeng/api";
import {InputNumberModule} from "primeng/inputnumber";


@NgModule({
  declarations: [DateFilterComponent],
  exports: [
    DateFilterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CalendarModule,
    DialogModule,
    OverlayPanelModule,
    ConfirmDialogModule,
    InputNumberModule,
  ],
  providers: [ConfirmationService]
})
export class DateFilterModule {}
