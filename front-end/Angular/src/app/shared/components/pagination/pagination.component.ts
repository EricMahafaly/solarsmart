import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Pagination} from "../../../models/core/pagination.model";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnInit{
  @Input({required: true}) pagination !: Pagination;
  @Input() showPageNumber: boolean = true
  pages!: number[]
  @Output() currentPage : EventEmitter<number> = new EventEmitter<number>()

  ngOnInit(): void {
    // this.currentPage.emit(this.pagination.currentPage)
    this.pages = Array.from({length: this.getTotalPage()}, (_, i) => i + 1);
  }

  getNumberOfFirstElement() : number{
    return ((this.pagination.currentPage - 1) * this.pagination.pageSize) + 1;
  }

  getNumberOfLastElement(): number{
    let num = this.pagination.currentPage * this.pagination.pageSize;

    console.log("num = ", num)
    return num < this.pagination.totalItems ? num : this.pagination.totalItems
  }

  getTotalPage(){
    return Math.ceil(this.pagination.totalItems / this.pagination.pageSize)
  }

  onChangePage(page: number){
    this.pagination.currentPage = page;
    this.currentPage.emit(this.pagination.currentPage)
  }

}
