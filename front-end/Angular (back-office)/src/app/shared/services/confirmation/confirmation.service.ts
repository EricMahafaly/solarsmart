import { Injectable } from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ConfirmationComponent} from "../../components/confirmation/confirmation.component";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ConfirmationService {

  private _confirm: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  constructor(private modalService: NgbModal) { }

  open(){
    const modalRef = this.modalService.open(ConfirmationComponent,
      {size: "sm", scrollable: false, keyboard: false, backdrop: "static", centered: true})

    // this._confirm = modalRef.componentInstance._confirm;

    console.log("modal ref = ", modalRef)

    modalRef.componentInstance.confirmChange.subscribe((data: boolean)=>{
      this._confirm.next(data)
    })
  }


  get confirm(): boolean {
    return this._confirm.value;
  }
}
