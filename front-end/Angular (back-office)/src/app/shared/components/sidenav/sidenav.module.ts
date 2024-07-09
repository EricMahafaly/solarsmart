import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {SidenavComponent} from "./sidenav.component";
import {RouterLink, RouterLinkActive, RouterModule} from "@angular/router";
import { SubLevelMenuComponent } from './sub-level-menu/sub-level-menu.component';



@NgModule({
  declarations: [SidenavComponent, SubLevelMenuComponent],
    imports: [
        CommonModule,
        RouterModule,
        NgOptimizedImage
    ],
  exports: [SidenavComponent]
})
export class SidenavModule { }
