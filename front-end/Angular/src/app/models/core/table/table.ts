import {TableColumnType} from "./table-column-type";

export interface ITable {
  api: string,
  headers: ITableHeader[]
}

export interface ITableHeader{
  key: string,
  type: TableColumnType,
  label: string
}
