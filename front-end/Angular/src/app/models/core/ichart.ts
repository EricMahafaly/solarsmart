import {IDateData} from "./date/idatedata";

export interface IDateStat{
  data: IDateData[],
  unit?: string,
  domain: string,
  chartLabel: string,

}
