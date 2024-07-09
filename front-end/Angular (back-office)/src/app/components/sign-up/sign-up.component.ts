import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {RegisterModel} from "../../models/api/register.model";
import {passwordValidator} from "../../shared/validator/password.validator";
import {AuthService} from "../../dashboard/services/authentication/auth.service";
import {ToastService} from "../../shared/services/toast/toast.service";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit{
  protected formGroup!: FormGroup
  protected isSubmitted: boolean = false

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private toastService: ToastService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(){
    this.formGroup = this.formBuilder.group({
      name: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.email, Validators.required]),
      password: new FormControl('', [passwordValidator(), Validators.required]),
      confirmPassword: new FormControl('', [Validators.required])
    })
  }

  toModel(formValue: any): RegisterModel{
    return {
      email: formValue.email,
      lastName: formValue.lastName,
      password: formValue.password,
      name: formValue.name,
      confirmedPassword: formValue.confirmPassword
    }
  }

  validatorCondition(validation: string){
    let control = this.formGroup.controls['password']
    if (control.hasError(validation)) return true;
    else if (control.hasError('required')) return true;
    return false;
  }

  confirmPassword(){
    const control = this.formGroup.controls['confirmPassword']
    const identique = control.value === this.formGroup.controls['password'].value
    const condition = identique && control.valid;

    // console.log("condition = ", condition)
    return condition
  }

  onSubmit(){

    this.formGroup.markAllAsTouched();

    const valid = this.formGroup.valid;

    if (valid){
      this.isSubmitted = true;
      let value = this.formGroup.value;
      this.authService.register(this.toModel(value))
        .subscribe(resp => {

          // console.log("log success = ", resp.success)

          if (resp.success){
            this.toastService.success(resp.message);
            this.router.navigate(['/user-space/home']).then()
          }else{
            this.isSubmitted = false;
            this.toastService.error(resp.message);
          }
        })
    }


    // if (this.formGroup.valid)
  }
}
