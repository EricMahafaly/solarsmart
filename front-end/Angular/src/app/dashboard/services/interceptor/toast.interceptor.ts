import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse, HttpErrorResponse
} from '@angular/common/http';
import {catchError, Observable, tap, throwError} from 'rxjs';
import {ApiResponse} from "../../../models/api/base/api.model";
import {ToastService} from "../../../shared/services/toast/toast.service";

@Injectable()
export class ToastInterceptor implements HttpInterceptor {

  constructor(private toastService: ToastService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    // if (request.method === 'POST'){
    //   return next.handle(request)
    //     .pipe(
    //       tap((event: HttpEvent<unknown> ) =>{
    //         if (event instanceof HttpResponse){
    //           if (event.body){
    //             const body = event.body as ApiResponse<any>
    //             if (!body.isSuccess)
    //             console.log("event = ", body)
    //           }
    //         }
    //       }),
    //     )
    // }


    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status >= 500) {
          this.toastService.error('Une erreur s\'est produite du côté du serveur.');
        } else if (error.status === 400) {
          const message = error.error && error.error.message ? error.error.message : 'Requête invalide.';
          // console.log("message = ", message)
          this.toastService.error(message);
        }
        return throwError(error);
      })
    );
  }
}
