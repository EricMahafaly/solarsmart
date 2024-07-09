export interface ReportModel{
  id: number,
  customerId: number,
  customerName: string,
  customerImage: string,
  createdDate: Date,
  closedDate: Date,
  description: string,
  state: ReportStateEnum
  comments ?: ReportComment[]
}

export interface ReportStateModel{
  id: number,
  state: ReportStateEnum,
  value: number
}

export enum ReportStateEnum{
  CLOSED = "CLOSED",
  IN_PROGRESS = "IN PROGRESS"
}

export interface ReportDetail{
  id: number,
  customerName: string,
  customerImage: string,
  comments: ReportComment[]
}

export interface ReportComment{
  id: number,
  text: string,
  date: Date,
  senderType: SenderType
}

export interface ReportCommentRequest{
  text: string,
  senderType: SenderType,
  adminId: number,
}


export enum SenderType{
  TECHNICIAN = 'TECHNICIAN',
  USER = 'USER'
}
