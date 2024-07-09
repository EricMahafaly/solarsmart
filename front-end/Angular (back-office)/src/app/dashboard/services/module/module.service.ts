import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environment";
import {map, Observable} from "rxjs";
import {ModuleSolarModel, ModuleSolarRegistration} from "../../../models/api/module.model";
import {ApiResponse} from "../../../models/api/base/api.model";
import {ListService} from "../core/list.service";
import {PageRequestModel} from "../../../models/api/base/page-request.model";
import {PageResponseModel} from "../../../models/api/base/page-response.model";
import {ModuleDetailModel} from "../../../models/api/module-detail.model";
import {ModuleOtherInfoDetailModel, ModuleOtherInfoModel} from "../../../models/api/info.model";
import {StatisticModuleModel} from "../../../models/api/statistic.model";

@Injectable({
  providedIn: 'root'
})
export class ModuleService {

  private baseApi: string = `${environment.apiUrl}/modules`;
  private additionalInfoApi: string = `${environment.apiUrl}/modules/additional-info`;
  constructor(private http: HttpClient, private listService: ListService<ModuleSolarModel>) { }

  getAll(pagination : PageRequestModel): Observable<PageResponseModel<ModuleSolarModel>>{
    let response = this.listService.all(this.baseApi, pagination);

    return response
      .pipe(map(resp => ({
        ...resp,
        items: resp.items.map(item => ({
          ...item,
          createdDate: new Date(item.createdDate)
        }))
      } as PageResponseModel<ModuleSolarModel>)));
  }

  getDetail(id: number): Observable<ModuleDetailModel>{
    return this.http.get<ApiResponse<ModuleDetailModel>>(`${this.baseApi}/${id}/info`)
      .pipe(
        map(resp => resp.data)
      )
  }

  saveOrUpdate(model: ModuleSolarRegistration, id?: number, update: boolean = false): Observable<ApiResponse<any>>{
    if (update && id){
      return this.update(id, model);
    }
    return this.save(model);
  }

  save(model: ModuleSolarRegistration): Observable<ApiResponse<any>>{
    return this.http.post<ApiResponse<any>>(`${this.baseApi}`, model);
  }

  update(id: number, model: ModuleSolarRegistration): Observable<ApiResponse<any>>{
    return this.http.put<ApiResponse<any>>(`${this.baseApi}/${id}`, model);
  }

  delete(id: number): Observable<ApiResponse<any>>{
    return this.http.delete<ApiResponse<any>>(`${this.baseApi}/${id}`);
  }

  getStatistic(): Observable<StatisticModuleModel>{
    return this.http.get<ApiResponse<StatisticModuleModel>>(`${this.baseApi}/statistic`)
      .pipe(map(resp => resp.data))
  }
}
