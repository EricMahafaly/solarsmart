export class ApiDataModel<T> {
    private _data!: T | null;
    private _loading: boolean = true

    constructor(data: T | null) {
        this._data = data;
    }

    get data(): T {
        if (this._data === null) {
            throw new Error("Data is null");
        }
        return this._data;
    }

    set data(value: T) {
        this._data = value;
    }

    get loading(): boolean {
        return this._loading;
    }

    set loading(value: boolean) {
        this._loading = value;
    }

    public isLoaded(){
        this._loading = false;
    }

    public haveData(): boolean{
        return !(this.loading || this._data === null);

    }
}
