import {TableColumnType} from "./table-column-type";

export class TableColumnInfoType extends TableColumnType{
  private _imageKey!: string;
  private _infoKey!: string;


  constructor(principalLabelKey: string,imageKey: string, nameKey: string) {
    super(principalLabelKey);
    this._imageKey = imageKey;
    this._infoKey = nameKey;
  }


  get imageKey(): string {
    return this._imageKey;
  }

  set imageKey(value: string) {
    this._imageKey = value;
  }

  get infoKey(): string {
    return this._infoKey;
  }

  set infoKey(value: string) {
    this._infoKey = value;
  }
}
