import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment";
import {BaseService} from "../core/base.service";
import {ComposantDetailsParamModel} from "../../../models/api/composant-details-param.model";
import {map, Observable} from "rxjs";
import {IComposantDetailModel} from "../../../models/api/composant.model";
import {ApiResponse} from "../../../models/api/base/api.model";
import {RelaisStateModel} from "../../../models/api/relais-state.model";
import {ComponentDetailDataModel} from "../../../models/core/component-detail-data.model";

@Injectable({
  providedIn: 'root'
})
export class ComposantService {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient, private baseService: BaseService) { }

  switchRelaisState(url: string) : Observable<RelaisStateModel>{
    return this.http.get<ApiResponse<RelaisStateModel>>(`${this.baseUrl}${url}`)
      .pipe(map( resp => resp.data))
  }

  getRelaiState(url: string): Observable<RelaisStateModel>{
    return this.http.get<ApiResponse<RelaisStateModel>>(`${this.baseUrl}${url}`)
      .pipe(map( resp => resp.data))
  }


  Filter(url: string, params: ComposantDetailsParamModel): Observable<ComponentDetailDataModel[]>{
    return this.http.get<ApiResponse<ComponentDetailDataModel[]>>(`${this.baseUrl}${url}`,
      {params: this.baseService.createParams(params.toJSON())})
      .pipe(map( resp => resp.data.map(detail => ({
        ...detail,
        date: new Date(detail.date)
      }))))
  }

  Search(url: string, params: {date: string}): Observable<ComponentDetailDataModel>{
    return this.http.get<ApiResponse<ComponentDetailDataModel>>(`${this.baseUrl}${url}`,
      {params: this.baseService.createParams(params)})
      .pipe(map(resp => ({
        ...resp.data,
        date: new Date(resp.data.date)
      })))
  }
}
