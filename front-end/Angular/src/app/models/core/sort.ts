export interface SortModel{
  field: string,
  direction: SortDirection
}

export interface SortFilterModel{
  label: string,
  active: boolean,
  sort: SortModel
}


export enum SortDirection{
  DESC = 'DESC',
  ASC = 'ASC'
}
