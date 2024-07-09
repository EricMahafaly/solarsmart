export interface IDateData {
  label: string,
  tag: string,
  data?: any,
}


export interface IDailyData extends IDateData{
  date: Date
}

export interface IWeeklyData {
  label: string,
  tag: string,
  number: number,
  start_date: Date,
  end_date: Date,
  days: IDailyData[]
}

export interface IMonthlyData extends IDateData{
  data: number,
  month: number,
  year: number,
}
