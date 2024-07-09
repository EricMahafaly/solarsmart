import { Injectable } from '@angular/core';
import {map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ApiService} from "./api.service";
import {environment} from "../../../environment";
import {FilterRequestModel, IFilterModel} from "../../../models/api/base/filter.model";
import {Pagination} from "../../../models/core/pagination.model";
import {PageResponseModel} from "../../../models/api/base/page-response.model";
import {PageRequestModel} from "../../../models/api/base/page-request.model";
import {ApiResponse} from "../../../models/api/base/api.model";

@Injectable({
  providedIn: 'root'
})
export class ListService<T> {

  constructor(private http: HttpClient, private apiService: ApiService<ApiResponse<PageResponseModel<T>>>) { }

  filter(url: string, criteria: FilterRequestModel | FilterRequestModel[], pagination ?: PageRequestModel): Observable<PageResponseModel<T>>{
    return this.apiService.post(`${url}`, criteria, pagination)
      .pipe(map(resp => resp.data))
  }

  // filter(url: string, criteria: FilterRequestModel | FilterRequestModel[], pagination?: PageRequestModel): Observable<PageResponseModel<T>> {
  //   let params = this.apiService.createParams(pagination);
  //
  //   // Ajoutez les paramètres de filtre à l'objet params
  //   if (Array.isArray(criteria)) {
  //     criteria.forEach((filter: FilterRequestModel) => {
  //       filter.criteria.forEach((searchCriteria: IFilterModel) => {
  //         params = params.append(searchCriteria.filterKey, searchCriteria.value);
  //       });
  //     });
  //   } else {
  //     criteria.criteria.forEach((searchCriteria: IFilterModel) => {
  //       params = params.append(searchCriteria.filterKey, searchCriteria.value);
  //     });
  //   }
  //
  //   // Effectuez la requête GET avec les paramètres de requête
  //   return this.http.get<PageResponseModel<T>>(`${url}`, { params });
  // }


  // filterGetting(url: string, criteria: FilterRequestModel | FilterRequestModel[], pagination ?: PageRequestModel): Observable<PageResponseModel<T>>{
  //   // return this.http.post<PageResponseModel<T>>(
  //   //   `${this.baseUrl}${url}`, criteria, {params: this.apiService.createParams(pagination)})
  //   return this.apiService.get(`${url}`, criteria, pagination)
  //     .pipe(map(resp => resp.data))
  // }

  all(url: string, pagination ?: PageRequestModel): Observable<PageResponseModel<T>>{
    return this.apiService.get(`${url}`, pagination)
      .pipe(map(resp => resp.data))
  }
}
