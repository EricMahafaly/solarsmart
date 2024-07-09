import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ListService} from "../core/list.service";
import {CustomerInfoModel, CustomerStatistic} from "../../../models/api/customer-info.model";
import {map, Observable} from "rxjs";
import {PageResponseModel} from "../../../models/api/base/page-response.model";
import {PageRequestModel} from "../../../models/api/base/page-request.model";
import {FilterRequestModel} from "../../../models/api/base/filter.model";
import {environment} from "../../../environment";
import {ApiResponse} from "../../../models/api/base/api.model";
import {CustomerDetailModel} from "../../../models/api/customer-detail.model";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseApiUrl = `${environment.apiUrl}/customers`

  constructor(private http: HttpClient, private listService: ListService<CustomerInfoModel>) { }

  getAll(pagination : PageRequestModel): Observable<PageResponseModel<CustomerInfoModel>>{
    return this.listService.all(this.baseApiUrl, pagination)
  }

  filter(pagination: PageRequestModel, criteria: FilterRequestModel): Observable<PageResponseModel<CustomerInfoModel>>{
    return this.listService.filter(`${this.baseApiUrl}/filter`, criteria, pagination);
  }

  getDetail(id: number): Observable<CustomerDetailModel>{
    return this.http.get<ApiResponse<CustomerDetailModel>>(`${this.baseApiUrl}/${id}/details`)
      .pipe(map(resp => resp.data))
  }

  getStatisticCurrentMonth(): Observable<CustomerStatistic>{
    return this.http.get<ApiResponse<CustomerStatistic>>(`${this.baseApiUrl}/statistic`)
      .pipe(map(resp => resp.data))
  }
}
