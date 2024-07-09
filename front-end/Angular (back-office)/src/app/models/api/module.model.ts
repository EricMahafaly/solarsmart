import {ModuleOtherInfoModel} from "./info.model";

export interface ModuleSolarModel {
  id: number,
  reference: string,
  createdDate: Date,
  used: boolean
}

export interface ModuleSolarRegistration{
  battery: {
    id?: number,
    puissance: number | null,
    voltage: number | null,
    marque: string
  },
  panel: {
    id?: number,
    puissance: number | null,
    voltage: number | null,
    marque: string
  },
  othersInfo: ModuleOtherInfoModel[]
}
