import { CanActivateFn } from '@angular/router';
import {AuthService} from "../services/authentication/auth.service";
import {inject} from "@angular/core";

export const authenticationGuard: CanActivateFn = (route, state) => {
  const authService: AuthService = inject(AuthService);

  if (authService.isAuthenticated()) return true;

  // console.log("ewa")

  authService.loginPage();

  return false;
};
