import {DateHelper} from "../../shared/helper/date-helper";

export class ComposantDetailsParamModel {
  private _date!: string
  private _startTime?: string
  private _endTime?: string


  get date(): string {
    return this._date;
  }

  set date(value: string) {
    this._date = value;
  }

  get startTime(): string | unknown {
    return this._startTime;
  }

  set startTime(value: string) {
    this._startTime = value;
  }

  get endTime(): string | unknown {
    return this._endTime;
  }

  set endTime(value: string) {
    this._endTime = value;
  }

  constructor(startTime: Date, endTime: Date | undefined, date: Date = new Date()) {
    this._date = DateHelper.extractDate(date);
    this._startTime = DateHelper.toTimeString(startTime);
    if (endTime != null) this._endTime = DateHelper.toTimeString(endTime);
  }

  public toJSON() : { date: string; start_time?: string; end_time?: string }{
    return {
      date: this._date,
      start_time: this._startTime,
      end_time: this._endTime
    }
  }

  public label(): string{
    return `${this.startTime} - ${this.endTime}`
  }
}

// export interface ComposantSearchDetailsParamModel{
//
// }
