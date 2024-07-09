import {CustomerInfoModel} from "./customer-info.model";

export interface CustomerDetailModel extends CustomerInfoModel{
  subscription?: string,
  moduleId: number,
  moduleReference: string
}
