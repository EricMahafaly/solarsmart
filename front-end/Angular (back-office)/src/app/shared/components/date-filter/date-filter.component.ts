import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {DateFilter} from "../../../models/core/date/date-filter";
import {AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {DateRangeModel} from "../../../models/core/date/date-range.model";
import {InputNumberInputEvent} from "primeng/inputnumber";

@Component({
  selector: 'app-date-filter',
  templateUrl: './date-filter.component.html',
  styleUrls: ['./date-filter.component.scss']
})
export class DateFilterComponent implements OnInit{

  protected readonly DateFilter = DateFilter;

  @Input() dateType!: DateFilter
  @Output() dates : EventEmitter<DateRangeModel> = new EventEmitter<DateRangeModel>()
  formData!: FormGroup;

  protected prefix:{hour_start?: string, minute_start?: string, hour_end?: string, minute_end?: string} | any  = {
  }

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(){
    this.formData = this.formBuilder.group({
      hour_start: [null, [Validators.required,this.hourValidator()]],
      minute_start: [null, [Validators.required, this.minuteValidator()]]
    })

    if (this.dateType === DateFilter.DATE_RANGE){
      this.formData.addControl('hour_end', new FormControl(null, [Validators.required, this.hourValidator]))
      this.formData.addControl('minute_end', new FormControl(null, [Validators.required, this.minuteValidator]))
    }
  }

  hourValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const hourValue = control.value;
      if (hourValue && (isNaN(hourValue) || hourValue > 24 || hourValue < 0)) {
        return { 'invalidHour': { value: hourValue } };
      }
      return null;
    };
  }

  minuteValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const minuteValue = control.value;
      if (minuteValue && (isNaN(minuteValue) || minuteValue > 59 || minuteValue < 0)) {
        return { 'invalidMinute': { value: minuteValue } };
      }
      return null;
    };
  }

  isValid(position: string):boolean{
    return !!((this.formData.get(`hour_${position}`)?.touched && this.formData.get(`hour_${position}`)?.errors)
      ||
      (this.formData.get(`minute_${position}`)?.errors && this.formData.get(`minute_${position}`)?.touched));
  }

  dateValid(date: DateRangeModel): boolean{
    if (date.end_date && date.start_date <= date.end_date) return true;
    else if (!date.end_date) return true;
    return false;
  }

  onSubmit(){

    this.formData.markAllAsTouched()
    this.formData.markAsDirty()

    if (this.formData.valid){
      let formValue = this.formData.value;
      let dateStart = new Date();
      dateStart.setHours(formValue.hour_start, formValue.minute_start, 0, 0);

      let datesExport: DateRangeModel = {
        start_date: dateStart
      }

      if (formValue.hour_end != undefined && formValue.minute_end != undefined){

        let endDate = new Date(dateStart)
        endDate.setHours(formValue.hour_end, formValue.minute_end, 0, 0)
        datesExport.end_date = endDate;
      }


      if (this.dateValid(datesExport)){
        this.dates.emit(datesExport);
      }
    }
  }
}
