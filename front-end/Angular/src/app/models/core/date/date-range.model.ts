export interface DateRangeModel {
  start_date: Date,
  end_date?: Date;
}

export function ToDateRange(dates: Date[]): DateRangeModel{
  return {
    start_date: dates[0],
    end_date: dates[1]
  }
}
