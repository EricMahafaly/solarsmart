export class TableColumnType {

  private _principalLabelKey!: string;

  constructor(principalLabel: string) {
    this._principalLabelKey = principalLabel;
  }

  get principalLabelKey(): string {
    return this._principalLabelKey;
  }

  set principalLabelKey(value: string) {
    this._principalLabelKey = value;
  }
}
