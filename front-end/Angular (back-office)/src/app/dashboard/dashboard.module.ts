import { Injectable , NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { LayoutComponent } from "./components/layout/layout.component";
import {SidenavModule} from "../shared/components/sidenav/sidenav.module";
import {SharedModule} from "../shared/shared.module";
import {NotFoundComponent} from "../components/not-found/not-found.component";
import {HeaderModule} from "../shared/components/header/header.module";
import {LoaderModule} from "../shared/components/loader/loader.module";
import {ToastMessageModule} from "../shared/components/toast-message/toast-message.module";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatNativeDateModule} from "@angular/material/core";

@Injectable({
  providedIn: 'root',
})

@NgModule({
  declarations: [LayoutComponent],
  exports: [RouterModule],
  imports: [
    SharedModule,
    RouterModule.forChild([
      {
        path: 'user-space',
        component: LayoutComponent,
        children: [
          {

            path: 'home',
            loadChildren: () => import('./components/home/home.module').then((m) => m.HomeModule)
          },
          {
            path: 'customers',
            loadChildren: () => import('./components/customer/customer-routing.module').then(m => m.CustomerRoutingModule)
          },
          {
            path: 'modules',
            loadChildren: () => import('./components/module/module-routing.module').then(m => m.ModuleRoutingModule)
          },
          {
            path: 'supports',
            loadChildren: () => import('./components/support/support.module').then(m => m.SupportModule)
          },{
            path: 'comments',
            loadChildren: () => import('./components/comment/comment.module').then(m => m.CommentModule)
          },
          {path: '', redirectTo: 'home', pathMatch: 'full'}
        ]
      },

      {path: '', redirectTo: 'user-space/home', pathMatch: 'full'},
      {path: '**', component: NotFoundComponent},
    ]),
    SidenavModule,
    HeaderModule,
    LoaderModule,
    ToastMessageModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
  ]
})
export class DashboardModule{}
