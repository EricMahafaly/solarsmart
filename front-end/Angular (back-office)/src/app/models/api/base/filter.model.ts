export interface IFilterModel {
  filterKey: string,
  operation: string,
  value?: any
}

export interface FilterRequestModel{
  dataOption: string,
  criteria: IFilterModel[]
}
