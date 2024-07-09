import {Directive, ElementRef, HostListener, Input, OnChanges, OnInit, Renderer2, SimpleChanges} from '@angular/core';
import { NgControl} from "@angular/forms";

@Directive({
  selector: '[validation]'
})
export class InputValidationDirective implements OnInit{
  constructor(
    private elRef: ElementRef,
    private renderer: Renderer2,
    private control: NgControl) {
  }

  ngOnInit(): void {
    this.control.valueChanges?.subscribe(()=>{
      this.validate();
    })

    this.onSubmit();
  }

  private validate(){
    if (this.control){

      if (this.control.touched && this.control.errors){
        this.addInvalidClass()
      }

      else this.removeClass();
    }

  }

  onSubmit(){
    const form = this.elRef.nativeElement.closest('form')
    if (form) {
      form.addEventListener('submit', () => {
        console.log("ici")
        this.validate()

      })
    }
    // if (form) this

  }

  @HostListener('blur')
  onInput(){
    this.validate();
  }

  private addInvalidClass(){
    this.renderer.removeClass(this.elRef.nativeElement, 'input-valid')
    this.renderer.addClass(this.elRef.nativeElement, 'input-required')
  }

  private addValidClass(){

    this.renderer.removeClass(this.elRef.nativeElement, 'input-required')
    this.renderer.addClass(this.elRef.nativeElement, 'input-valid')
  }
  private removeClass(){
    this.renderer.removeClass(this.elRef.nativeElement, 'input-required')
  }

}
