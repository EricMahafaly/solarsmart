import {Pagination} from "../../core/pagination.model";

export class PageResponseModel<T> {
  private _items!: T[];
  private _totalItems!: number;
  private _totalPages!: number;

  get items(): T[] {
    return this._items;
  }

  set items(value: T[]) {
    this._items = value;
  }

  get totalItems(): number {
    return this._totalItems;
  }

  set totalItems(value: number) {
    this._totalItems = value;
  }

  get totalPages(): number {
    return this._totalPages;
  }

  set totalPages(value: number) {
    this._totalPages = value;
  }

  // createPagination(): Pagination{
  //   const page = new Pagination();
  //   page.
  // }
}
