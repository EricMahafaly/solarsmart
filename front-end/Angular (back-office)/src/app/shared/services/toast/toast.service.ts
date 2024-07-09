import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {ToastType} from "../../../models/core/toast";

export const toastTypeData = {
  info: {classe: 'info-toast', icon: 'fa-solid fa-circle-info'},
  success: {classe: 'success-toast', icon: 'fa-solid fa-circle-check'} ,
  warning: {classe: 'warning-toast', icon: 'fa-solid fa-circle-exclamation'},
  danger: {classe: 'danger-toast', icon: 'fa-solid fa-triangle-exclamation'}
};

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  // The boolean that drives the toast's 'open' vs. 'close' behavior
  public shows: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  // The message string that'll bind and display on the toast
  public message: BehaviorSubject<string> = new BehaviorSubject<string>('Default Toast Message');
  private isPersisted: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);


  public state: BehaviorSubject<ToastType> = new BehaviorSubject<ToastType>(toastTypeData.success);
  public timeOut: BehaviorSubject<number> = new BehaviorSubject<number>(100);
  public closeButton: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor() {
  }

  persistToast(): void {
    this.isPersisted.next(true);
  }

  resetPersistedState(): void {
    this.isPersisted.next(false);
  }

  // Méthode pour vérifier l'état de la persistance
  isToastPersisted(): Observable<boolean> {
    return this.isPersisted.asObservable();
  }

  show(toastState: ToastType, toastMsg: string, timeOut: number = 1000, closeButton: boolean = false): void {
    this.state.next(toastState);
    this.message.next(toastMsg);
    this.shows.next(true);
    this.timeOut.next(timeOut)
    this.closeButton.next(closeButton)

    setTimeout(() => {
      this.dismissToast();
    }, timeOut);

    // this.isToastPersisted().subscribe((isPersisted) => {
    //
    //   setTimeout(() => {
    //     this.dismissToast();
    //   }, timeOut);
    // });

  }

  error(toastMsg: string, timeOut: number = 4000, closeButton: boolean = false): void{
    this.show(toastTypeData.danger, toastMsg, timeOut, closeButton);
  }

  success(toastMsg: string, timeOut: number = 4000, closeButton: boolean = false): void{
    this.show(toastTypeData.success, toastMsg, timeOut, closeButton);
  }

  info(toastMsg: string, timeOut: number = 4000, closeButton: boolean = false): void{
    this.show(toastTypeData.info, toastMsg, timeOut, closeButton);
  }

  // This updates the showsToast behavioursubject to 'false'
  dismissToast(): void {
    this.shows.next(false);
  }
}
