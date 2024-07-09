import {Component, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";
import {debounceTime} from "rxjs";
import {Pagination} from "../../../../models/core/pagination.model";
import {ApiDataModel} from "../../../../models/api/base/api-data.model";
import {CustomerInfoModel} from "../../../../models/api/customer-info.model";
import {CustomerService} from "../../../services/customer/customer.service";
import {FilterRequestModel} from "../../../../models/api/base/filter.model";
import {PageResponseModel} from "../../../../models/api/base/page-response.model";
import {Sort} from "@angular/material/sort";

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss']
})
export class CustomerListComponent implements OnInit{
  pagination!: Pagination
  customerData: ApiDataModel<CustomerInfoModel[]> = new ApiDataModel<CustomerInfoModel[]>(null);

  searchControl: FormControl = new FormControl('')
  filterRequest?: FilterRequestModel

  constructor(private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.pagination = new Pagination();
    this.pagination.pageSize = 5;

    // this.setCustomerData();
    this.filter()

    this.searchControl.valueChanges.pipe(
      debounceTime(300)
    ).subscribe( term => {
      this.customerData.loading = true;
      this.filter(term)
    })

  }

  getRow(){
    return new Array(5);
  }

  // setCustomerData(){
  //   this.customerService.getAll(this.pagination.toRequest())
  //     .subscribe((resp: PageResponseModel<CustomerInfoModel>) => {
  //       this.customerData.data = resp.items
  //       this.pagination.totalItems = resp.totalItems;
  //       this.customerData.isLoaded();
  //
  //     })
  // }

  filter(name: string = this.searchControl.value){


    // this.customerData.loading = true;

    const criteria: FilterRequestModel = {
      criteria: [
        {
          filterKey: 'name',
          value: name,
          operation: 'cn'
        },
        {
          filterKey: 'lastName',
          value: name,
          operation: 'cn'
        }
      ],
      dataOption: 'any'
    }

    this.filterRequest = criteria;
    this.customerData.loading = true;

    this.customerService.filter(this.pagination.toRequest(), criteria)
      .subscribe(resp => {
        this.customerData.data = resp.items
        this.pagination.totalItems = resp.totalItems;
        this.customerData.isLoaded();
      })
  }

  changePage(page: number){
    this.pagination.currentPage = page
    this.filter()
  }

  sort(sort: Sort) {
    this.pagination.sortBy = sort.active
    this.pagination.direction = sort.direction
    this.filter();
  }
}
