import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ComposantDetailsParamModel} from "../../../models/api/composant-details-param.model";
import {map, Observable} from "rxjs";
import {ComponentDetailDataModel} from "../../../models/core/component-detail-data.model";
import {environment} from "../../../environment";
import {ComposantService} from "../composant/composant.service";
import {IMonthlyData, IWeeklyData} from "../../../models/core/date/idatedata";
import {ApiResponse} from "../../../models/api/base/api.model";
import {PanelInfoModel} from "../../../models/api/info.model";
import {IComposantDetailModel} from "../../../models/api/composant.model";
import {BaseService} from "../core/base.service";
import {RelaisStateModel} from "../../../models/api/relais-state.model";
import {PanelDistinctModel} from "../../../models/api/simple.model";

@Injectable({
  providedIn: 'root'
})
export class PanelService {

  private baseUrl = `${environment.apiUrl}/panels`
  private statApi = `${environment.apiUrl}/statistics/panels`

  constructor(private http: HttpClient,
              private composantService: ComposantService,
              private baseService: BaseService) { }

  getAllDistinct(): Observable<PanelDistinctModel[]>{
    return this.http.get<ApiResponse<PanelDistinctModel[]>>(`${this.baseUrl}/distinct`)
      .pipe(map( resp => resp.data))
  }

  getProductionMonthly(year: number, moduleId: number): Observable<IMonthlyData[]>{
    return this.http.get<ApiResponse<IMonthlyData[]>>(`${this.statApi}/production/yearly/${year}/modules/${moduleId}`)
      .pipe(
        map(resp => resp.data)
      )
  }

  getProductionWeekly(year: number, month: number, moduleId: number): Observable<IWeeklyData[]>{
    return this.http.get<ApiResponse<IWeeklyData[]>>(
      `${this.statApi}/production/monthly/${year}/${month}/modules/${moduleId}`)
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
    let url = `/statistics/panels/filter/modules/${moduleId}`
    return this.composantService.Filter(url, filter);
  }

  getInfo(moduleId: number): Observable<PanelInfoModel>{
    return this.http.get<ApiResponse<PanelInfoModel>>(`${this.baseUrl}/info/modules/${moduleId}`)
      .pipe(map(resp => resp.data))
  }

  getProduction(moduleId: number, params: ComposantDetailsParamModel): Observable<IComposantDetailModel[]> {
    let reponse = this.http.get<ApiResponse<IComposantDetailModel[]>>(`${this.statApi}/productions/modules/${moduleId}`,
      {params: this.baseService.createParams(params.toJSON())});

    reponse.subscribe((resp) =>{
      console.log("response = ", resp)
    })

    return reponse.pipe(map(resp => resp.data.map(comp => ({
      ...comp,
      date: new Date(comp.date)
    }))));
    // return this.http.get<ApiResponse<IComposantDetailModel[]>>(`${this.statApi}/productions/modules/${moduleId}`,
    //   {params: this.baseService.createParams(params.toJSON())})
    //   .pipe(map(resp => resp.data.map(comp => ({
    //     ...comp,
    //     date: new Date(comp.date)
    //   }))))
  }

  sumProduction(moduleId: number, params: ComposantDetailsParamModel): Observable<number> {
    return this.http.get<ApiResponse<number>>(`${this.statApi}/production/modules/${moduleId}`,
      {params: this.baseService.createParams(params.toJSON())})
      .pipe(map(resp => resp.data))
  }

  findDataDetail(moduleId: number, filter: { date: string }): Observable<ComponentDetailDataModel> {
    let url = `/statistics/panels/modules/${moduleId}`
    return this.composantService.Search(url, filter)
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
