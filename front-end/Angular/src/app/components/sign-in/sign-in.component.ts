import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {passwordValidator} from "../../shared/validator/password.validator";
import {AuthService} from "../../dashboard/services/authentication/auth.service";
import {LoginModel} from "../../models/api/login.model";
import {ToastService} from "../../shared/services/toast/toast.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit{
  protected formGroup!: FormGroup;
  protected isSubmitted: boolean = false;
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
      email: new FormControl('', [Validators.required ,Validators.email]),
      password: new FormControl('', [Validators.required])
    })
  }



  onSubmit(){
    this.isSubmitted = true;
    this.formGroup.markAllAsTouched();

    const valid = this.formGroup.valid;
    if (valid){
      let val = this.formGroup.value as LoginModel;
      this.authService.login(val)
        .subscribe(resp => {

          if (resp.success){
            // this.toastService.success(resp.message)
            this.authService.storeInformation(resp.data)
            this.router.navigate(['/user-space/home']).then()
          }else{
            this.isSubmitted = false;
            this.toastService.error(resp.message)
          }
        })
    }
  }
}
