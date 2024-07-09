import {formatDate} from "@angular/common";
import {DateRangeModel} from "../../models/core/date/date-range.model";

export class DateHelper {

  public static getAllHourInDate(date: Date = new Date()): Date[]{
    let dates: Date[] = new Array(24)
    for (let i = 0; i < 24; i++) {
      const hour = new Date(date.getFullYear(), date.getMonth(), date.getDate(), i);

      dates[i] = hour;
    }

    return dates;

  }

  public static getPeriod(date: Date) {
    const period = date.toJSON().split("-");
    return period[0] + "-" + period[1];
  }

  public static getDateRange(date: Date = new Date()): DateRangeModel {
    const dates = this.getAllHourInDate(date);
    const size = dates.length;
    let ranges: DateRangeModel = {start_date: date, end_date: date};
    for (let j = 0; j < size - 1; j++) {
      if (date >= dates[j] && date <= dates[j+1]){
        ranges = {
          start_date: dates[j],
            end_date: dates[j+1]
        }
        return ranges;
      }
    }

    return ranges;
  }

  public static isBetween(date: Date, startDate: Date, enDate: Date): boolean{
    return startDate <= date && enDate >= date;
  }

  public static toDateTimeString(date: Date): string{
    return  date.toDateString() + ' ' + date.toLocaleTimeString()
  }

  public static toTimeString(date: Date){
    // return date.
    return formatDate(date, 'HH:mm', "en-US")
  }

  public static toISOString(date: Date){
    return formatDate(date, 'yyyy-MM-ddTHH:mm:ss', "en-us")
  }

  public static extractDate(date: Date){
    return formatDate(date, 'yyyy-MM-dd', "en-Us")
  }

}
