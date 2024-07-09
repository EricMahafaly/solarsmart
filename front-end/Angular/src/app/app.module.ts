import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AppRoutingModule} from "./app-routing.module";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgCircleProgressModule } from 'ng-circle-progress';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {LoaderModule} from "./shared/components/loader/loader.module";
import {LoaderInterceptor} from "./dashboard/services/interceptor/loader.interceptor";
import {AuthInterceptor} from "./dashboard/services/interceptor/auth.interceptor";
import {ErrorInterceptor} from "./dashboard/services/interceptor/error.interceptor";
import { PlaceholderModule } from '@coreui/angular';
import {NgxSkeletonLoaderModule} from "ngx-skeleton-loader";
import {ToastInterceptor} from "./dashboard/services/interceptor/toast.interceptor";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {ToastMessageModule} from "./shared/components/toast-message/toast-message.module";
import {SocketIoConfig, SocketIoModule} from "ngx-socket-io";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FontAwesomeModule,
    LoaderModule,
    PlaceholderModule,
    NgbModule,
    NgxSkeletonLoaderModule.forRoot({animation: 'progress-dark', loadingText: 'This item is actually loading...'}),
    NgCircleProgressModule.forRoot({
      // set defaults here
      // radius: 100,
      // outerStrokeWidth: 16,
      // innerStrokeWidth: 8,
      // outerStrokeColor: "#78C000",
      // innerStrokeColor: "#C7E596",
      // animationDuration: 300,
    }),
    NgbModule,
    ToastMessageModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoaderInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ToastInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
