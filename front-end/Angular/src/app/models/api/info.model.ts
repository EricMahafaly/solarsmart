import {RelaisStateModel} from "./relais-state.model";
import {ActionType} from "../core/action";

export interface BatteryInfoModel{
  id: number,
  relais_state: RelaisStateModel,
  puissance: number,
  voltage: number,
  marque: string,
}

export interface PanelInfoModel {
  id: number,
  relais_state: RelaisStateModel,
  puissance: number,
  voltage: number,
  marque: string,
}

export interface ModuleOtherInfoModel{
  id?: number,
  description?: string,
  name: string,
  details: ModuleOtherInfoDetailModel[]
}

export interface ModuleOtherInfoDetailModel{
  id?: number,
  key: string,
  value: string,
  description?: string
}

export interface AdditionalListResult{
  data: ModuleOtherInfoModel[],
  deletes: ModuleOtherInfoModel[],
  detailsDeletes: ModuleOtherInfoDetailModel[]
}

export interface AdditionalInfoModule{
  action?: ActionType;
  info: ModuleOtherInfoModel;
}

export interface UserInfo{
  id: number,
  email: string,
  name: string,
  lastName: string,
}
