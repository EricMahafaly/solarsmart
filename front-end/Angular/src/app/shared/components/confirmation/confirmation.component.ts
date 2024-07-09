import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {ConfirmationService} from "../../services/confirmation/confirmation.service";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent {

  @Input() confirm : boolean = false
  @Output() confirmChange: EventEmitter<boolean> = new EventEmitter<boolean>()

  constructor(private activeModal: NgbActiveModal) {
  }

  cancel() {
    this.confirmChange.emit(false)
    this.activeModal.close()
  }

  valid(){
    this.confirmChange.emit(true)
    this.activeModal.close()
  }
}
