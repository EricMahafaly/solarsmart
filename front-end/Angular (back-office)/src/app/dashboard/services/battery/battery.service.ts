import { Injectable } from '@angular/core';
import {environment} from "../../../environment";
import {map, Observable} from "rxjs";
import {IMonthlyData, IWeeklyData} from "../../../models/core/date/idatedata";
import {ApiResponse} from "../../../models/api/base/api.model";
import {HttpClient} from "@angular/common/http";
import {ComposantDetailsParamModel} from "../../../models/api/composant-details-param.model";
import {ComponentDetailDataModel} from "../../../models/core/component-detail-data.model";
import {IComposantDetailModel} from "../../../models/api/composant.model";
import {ComposantService} from "../composant/composant.service";
import {BaseService} from "../core/base.service";
import {BatteryInfoModel} from "../../../models/api/info.model";
import {BatteryDistinctModel} from "../../../models/api/simple.model";
import {RelaisStateModel} from "../../../models/api/relais-state.model";
import {saveAs} from "file-saver";
import {DateHelper} from "../../../shared/helper/date-helper";

@Injectable({
  providedIn: 'root'
})
export class BatteryService {
  private statBaseApi = `${environment.apiUrl}/statistics/batteries`;
  private baseUrl = `${environment.apiUrl}/batteries`;
  constructor(private http: HttpClient, private componentService: ComposantService,
              private baseService: BaseService) { }

  getAllDistinct(): Observable<BatteryDistinctModel[]>{
    return this.http.get<ApiResponse<BatteryDistinctModel[]>>(`${this.baseUrl}/distinct`)
      .pipe(map( resp => resp.data))
  }

  getInfo(moduleId: number): Observable<BatteryInfoModel>{
    return this.http.get<ApiResponse<BatteryInfoModel>>(`${this.baseUrl}/info/modules/${moduleId}`)
      .pipe(map(resp => resp.data))
  }

  getPercentage(moduleId: number): Observable<number>{
    return this.http.get<ApiResponse<number>>(`${this.baseUrl}/detail/percentage/modules/${moduleId}`)
      .pipe(map(resp => resp.data))
  }

  getTimeUsageWeekly(year: number, month: number, moduleId: number): Observable<IWeeklyData[]>{
    return this.http.get<ApiResponse<IWeeklyData[]>>(
      `${this.statBaseApi}/time-usage/monthly/${year}/${month}/modules/${moduleId}`)
      .pipe(
        // map(resp => resp.data)
        map(resp => resp.data.map(weeklyData => ({
          ...weeklyData,
          start_date: new Date(weeklyData.start_date),
          end_date: new Date(weeklyData.end_date)
        })))
      )
  }

  getTimeUsageMonthly(year: number, moduleId: number): Observable<IMonthlyData[]>{
    return this.http.get<ApiResponse<IMonthlyData[]>>(`${this.statBaseApi}/time-usage/yearly/${year}/modules/${moduleId}`)
      .pipe(
        map(resp => resp.data)
      )
  }



  getDataDetail(moduleId: number, filter: ComposantDetailsParamModel) : Observable<ComponentDetailDataModel[]>{
    let url = `/statistics/batteries/filter/modules/${moduleId}`
    return this.componentService.Filter(url, filter);
  }

  findDataDetail(moduleId: number, filter: {date: string}) : Observable<ComponentDetailDataModel>{
    let url = `/statistics/batteries/modules/${moduleId}`
    return this.componentService.Search(url, filter)
  }

  getEnergyData(moduleId: number, params: ComposantDetailsParamModel): Observable<IComposantDetailModel[]>{
    return this.http.get<ApiResponse<IComposantDetailModel[]>>(`${this.statBaseApi}/energies/modules/${moduleId}`,
      {params: this.baseService.createParams(params.toJSON())})
      .pipe(map(resp => resp.data.map(comp => ({
        ...comp,
        date: new Date(comp.date)
      }))))
  }

  exportExcel(): void {
    this.http.get(`${this.baseUrl}/data/export/excel/all`, { responseType: 'blob' })
      .subscribe((data: Blob) => {

        const blob = new Blob([data], { type: 'application/octet-stream' });
        const filename = `battery_data_${DateHelper.toISOString(new Date())}.xlsx`;
        saveAs(blob, filename);
      });
  }

  switchRelais(moduleId: number): Observable<ApiResponse<RelaisStateModel>>{
    return this.http.get<ApiResponse<RelaisStateModel>>(`${this.baseUrl}/relais/switch/modules/${moduleId}`);
  }
}
