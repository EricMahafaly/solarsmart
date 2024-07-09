import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {authenticationGuard} from "./dashboard/guard/authentication.guard";
import {UnauthorizedComponent} from "./components/unauthorized/unauthorized.component";

const routes: Routes = [
  {
    path: 'sign-in',
    title:'Sign In',
    loadChildren: () =>
      import('./components/sign-in/sign-in.module').then(
        (m) => m.SignInModule
      ),
  },
  {
    path: 'sign-up',
    title:'Sign Up',
    loadChildren: () =>
      import('./components/sign-up/sign-up.module').then(
        (m) => m.SignUpModule
      ),
  },

  {
    path: 'unauthorized',
    component: UnauthorizedComponent
  },
  {
    path: '',
    title : "Dashboard",
    canActivate: [authenticationGuard],
    loadChildren: () =>
      import('./dashboard/dashboard.module').then(
        (m) => m.DashboardModule
      ),
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
