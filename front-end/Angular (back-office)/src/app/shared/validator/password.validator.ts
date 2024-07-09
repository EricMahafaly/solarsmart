import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function passwordValidator(): ValidatorFn {
  return (control: AbstractControl) : ValidationErrors | null => {
    const password = control.value;
    if (!password) return null

    if (password.length < 8){
      return { 'minLength': true};
    }

    const hasUpperCase = /[A-Z]/.test(password)
    const hasLowerCase = /[a-z]/.test(password)
    const hasNumber = /[0-9]/.test(password)

    if (!hasUpperCase || !hasLowerCase || !hasNumber) return {'pattern': true}

    return null;
  };
}
