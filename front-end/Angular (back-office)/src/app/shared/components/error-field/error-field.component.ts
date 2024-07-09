import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AbstractControl} from "@angular/forms";

@Component({
  selector: 'app-error-field',
  templateUrl: './error-field.component.html',
  styleUrls: ['./error-field.component.scss']
})
export class ErrorFieldComponent implements OnInit{
  @Input() show: boolean = false;
  @Input() message!: string;
  @Input() valid: boolean = false;
  @Input() control ?: AbstractControl;

  ngOnInit(): void {
    this.defaultMessage();
  }

  isShow(): boolean{
    if (this.control){
      if (this.control.touched || this.control.dirty) this.show = true;
    }
    return this.show;
  }

  isValid(){
    if (this.control){
      this.valid = !this.control.invalid;
    }
    // console.log("this.valid = ", this.valid)
    return this.valid;
  }

  defaultMessage(){
    if (!this.message) this.message = 'Ce champ est requis'
  }

  getIconClass(): string{
    if (this.isValid()) return "fa fa-check";
    return "fa fa-exclamation-triangle"
  }

  getColorClass() : string{
    if (this.isValid()) return "success";
    return "danger";
  }

  // successMessage(){
  //   if (!this.message && this.valid) this.message = 'Ce champ est valid'
  // }
}
