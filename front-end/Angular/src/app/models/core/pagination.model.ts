import {PageRequestModel} from "../api/base/page-request.model";

export class Pagination {
  currentPage!: number;
  pageSize!: number;
  totalItems!: number;
  sortBy: string = 'id';
  direction: string = 'ASC';

  constructor() {
    this.currentPage = 1;
    this.pageSize = 10
  }

  setDirection(order: number): void{
    if (order == 1){
      this.direction = "ASC";
      return ;
    }
    else if(order === -1) {
      this.direction = "DESC";
      return;
    }
  }


  setCurrentPage(first: number){
    this.currentPage = first / this.pageSize + 1;
  }

  public toRequest(): PageRequestModel{
    let request: PageRequestModel = {
      page: this.currentPage,
      size: this.pageSize,
      sortBy: this.sortBy,
      direction: this.direction
    }

    return request;
  }
}
