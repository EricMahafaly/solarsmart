import { Injectable } from '@angular/core';
import {map, Observable} from "rxjs";
import {IMonthlyData, IWeeklyData} from "../../../models/core/date/idatedata";
import {ApiResponse} from "../../../models/api/base/api.model";
import {ComposantDetailsParamModel} from "../../../models/api/composant-details-param.model";
import {ComponentDetailDataModel} from "../../../models/core/component-detail-data.model";
import {RelaisStateModel} from "../../../models/api/relais-state.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ComposantService} from "../composant/composant.service";
import {environment} from "../../../environment";
import {BaseService} from "../core/base.service";
import {IComposantDetailModel} from "../../../models/api/composant.model";

@Injectable({
  providedIn: 'root'
})
export class PriseService {

  private statApi = `${environment.apiUrl}/statistics/prises`

  private baseUrl: string = `${environment.apiUrl}/prises`;

  constructor(private http: HttpClient,
              private composantService: ComposantService,
              private baseService: BaseService) { }

  getConsommationMonthly(year: number, moduleId: number): Observable<IMonthlyData[]>{
    return this.http.get<ApiResponse<IMonthlyData[]>>(`${this.statApi}/consommation/yearly/${year}/modules/${moduleId}`)
      .pipe(
        map(resp => resp.data)
      )
  }

  getConsommationWeekly(year: number, month: number, moduleId: number): Observable<IWeeklyData[]>{
    return this.http.get<ApiResponse<IWeeklyData[]>>(
      `${this.statApi}/consommation/monthly/${year}/${month}/modules/${moduleId}`)
      .pipe(
        // map(resp => resp.data)
        map(resp => resp.data.map(weeklyData => ({
          ...weeklyData,
          start_date: new Date(weeklyData.start_date),
          end_date: new Date(weeklyData.end_date)
        })))
      )
  }

  getDataDetail(moduleId: number, filter: ComposantDetailsParamModel) : Observable<ComponentDetailDataModel[]>{
    let url = `/statistics/prises/filter/modules/${moduleId}`
    return this.composantService.Filter(url, filter);
  }

  getInfo(moduleId: number): Observable<RelaisStateModel>{

    return this.composantService.getRelaiState(`/prises/info/modules/${moduleId}`)
  }

  findDataDetail(moduleId: number, filter: { date: string }): Observable<ComponentDetailDataModel> {
    let url = `/statistics/prises/modules/${moduleId}`
    return this.composantService.Search(url, filter)
  }

  sumConsommation(moduleId: number, params: ComposantDetailsParamModel): Observable<number> {
    return this.http.get<ApiResponse<number>>(`${this.statApi}/consommation/modules/${moduleId}`,
      {params: this.baseService.createParams(params.toJSON())})
      .pipe(map(resp => resp.data))
  }

  getConsommationData(moduleId: number, params: ComposantDetailsParamModel): Observable<IComposantDetailModel[]> {
    return this.http.get<ApiResponse<IComposantDetailModel[]>>(`${this.statApi}/consommations/modules/${moduleId}`,
      {params: this.baseService.createParams(params.toJSON())})
      .pipe(map(resp => resp.data.map(comp => ({
        ...comp,
        date: new Date(comp.date)
      }))))
  }

  exportExcel(): Observable<Blob>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/octet-stream',
      'Accept': 'application/octet-stream'
    });
    let url = `${this.baseUrl}/data/export/excel/all`
    return this.http.get(url, {headers: headers, responseType: 'blob'})

  }

  switchRelais(moduleId: number): Observable<ApiResponse<RelaisStateModel>>{
    return this.http.get<ApiResponse<RelaisStateModel>>(`${this.baseUrl}/relais/switch/modules/${moduleId}`);
  }
}
