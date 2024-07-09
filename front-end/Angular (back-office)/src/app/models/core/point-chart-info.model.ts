export interface PointChartInfoModel {
  index?: number,
  domain?: string,
  info: {
    label: string,
    data: any,
    unit?: string
  }[],
}
