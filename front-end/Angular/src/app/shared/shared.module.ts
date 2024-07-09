import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from "@angular/router";
import { NgChartsModule } from 'ng2-charts';


@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    RouterModule,
    NgChartsModule
  ],

    exports: [
        CommonModule,
        RouterModule
    ]
})
export class SharedModule { }
