import {CustomerInfoModel} from "./customer-info.model";
import {BatteryInfoModel, ModuleOtherInfoModel, PanelInfoModel} from "./info.model";
import {ModuleSolarRegistration} from "./module.model";

export class ModuleDetailModel {

  private _id!: number;
  private _reference!: string;
  private _createdDate!: Date;
  private _customer?: CustomerInfoModel;
  private _used!: boolean;
  private _battery!: BatteryInfoModel;
  private _panel!: PanelInfoModel;
  private _othersInfo!: ModuleOtherInfoModel[];

  // public getRegister(): ModuleSolarRegistration{
  //   return {
  //     othersInfo: this.othersInfo,
  //     panel: {
  //       puissance: this.panel.puissance,
  //       voltage: this.panel.voltage,
  //       id: this.panel.id,
  //       marque: this.panel.marque
  //     },
  //     battery: {
  //       puissance: this.battery.puissance,
  //       voltage: this.battery.voltage,
  //       id: this.battery.id,
  //       marque: this.battery.marque
  //     }
  //   }
  // }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }


  get reference(): string {
    return this._reference;
  }

  set reference(value: string) {
    this._reference = value;
  }

  get createdDate(): Date {
    return this._createdDate;
  }

  set createdDate(value: Date) {
    this._createdDate = value;
  }

  get customer(): CustomerInfoModel | undefined {
    return this._customer;
  }

  set customer(value: CustomerInfoModel) {
    this._customer = value;
  }

  get used(): boolean {
    return this._used;
  }


  get battery(): BatteryInfoModel {
    return this._battery;
  }

  set battery(value: BatteryInfoModel) {
    this._battery = value;
  }

  get panel(): PanelInfoModel {
    return this._panel;
  }

  set panel(value: PanelInfoModel) {
    this._panel = value;
  }

  set used(value: boolean) {
    this._used = value;
  }

  get othersInfo(): ModuleOtherInfoModel[] {
    return this._othersInfo;
  }

  set othersInfo(value: ModuleOtherInfoModel[]) {
    this._othersInfo = value;
  }

}
