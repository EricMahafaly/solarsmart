import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function numberValidator(
  defaultValue: number, ...rangeValidators: ValidatorFn[]): ValidatorFn {

  return (control: AbstractControl): ValidationErrors | null => {
    if (control.value == defaultValue) return null;

    for (let validator of rangeValidators) {
      if (validator(control)) return validator(control);
    }

    return null;
  };
}
