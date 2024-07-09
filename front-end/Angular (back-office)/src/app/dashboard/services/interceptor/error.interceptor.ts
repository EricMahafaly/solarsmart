import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {ToastService} from "../../../shared/services/toast/toast.service";
import {Router} from "@angular/router";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private toastService: ToastService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status == 401 ){
          // this.toastService.error("Vous n'êtes pas authorisé à accéder a cette ressource")
          // this.router.navigate(['/sign-in'])
          this.router.navigate(['/unauthorized'])
        }
        // Gestion de l'erreur ici
        console.error(error);
        return throwError(error);
      })
    );
  }
}
