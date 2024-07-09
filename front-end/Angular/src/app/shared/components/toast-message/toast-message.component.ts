import {Component, forwardRef, OnInit} from '@angular/core';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {ToastService} from "../../services/toast/toast.service";


export enum toastMessageType{
  success= 'success',
  info = 'info',
  warning = 'warning',
  error = 'error',
}

@Component({
  selector: 'app-toast-message',
  templateUrl: './toast-message.component.html',
  styleUrls: ['./toast-message.component.scss'],
  animations: [
    trigger('toastTrigger', [ // This refers to the @trigger we created in the template
      state('open', style({ transform: 'translateX(-50%)' })), // This is how the 'open' state is styled
      state('close', style({ transform: 'translate(-50%, -200%)' })), // This is how the 'close' state is styled
      transition('open <=> close', [ // This is how they're expected to transition from one to the other
        animate('300ms ease-in-out')
      ])
    ])
  ]
})
export class ToastMessageComponent implements OnInit{

  constructor(public toast: ToastService) { }

  ngOnInit(): void {
    // console.log(this.toast.timeOut)


  }

  dismiss(): void {
    this.toast.dismissToast();
  }

  // show(){
  //
  //   // [@toastTrigger]="(toast.shows | async) ? 'open' : 'close'"
  //
  //   let toastShows = this.toast.shows;
  //
  //   if (toastShows.getValue()){
  //     const time = this.toast.timeOut.getValue();
  //     setTimeout(() => {
  //       this.dismiss();
  //     }, time);
  //   }
  //
  //   return toastShows;
  // }

  onPersist(): void {
    console.log("hey")
    this.toast.persistToast();
  }

  show() {
    let toastShows = this.toast.shows;

    // if (toastShows.getValue()) {
    //   const time = this.toast.timeOut.getValue();
    //   setTimeout(() => {
    //     this.dismiss();
    //   }, time);
    // }

    return toastShows;
  }


  // onPersist(){
  //
  // }

}
