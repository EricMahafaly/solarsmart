import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ListService} from "../core/list.service";
import {RatingModel} from "../../../models/api/rating.model";
import {environment} from "../../../environment";
import {map, Observable} from "rxjs";
import {ApiResponse} from "../../../models/api/base/api.model";
import {PageRequestModel} from "../../../models/api/base/page-request.model";
import {PageResponseModel} from "../../../models/api/base/page-response.model";
import {CustomerInfoModel} from "../../../models/api/customer-info.model";
import {FilterRequestModel} from "../../../models/api/base/filter.model";
import {ReportModel} from "../../../models/api/report.model";

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private baseApi: string = `${environment.apiUrl}/ratings`

  constructor(private http: HttpClient,  private listService: ListService<RatingModel>) { }

  list(pagination: PageRequestModel): Observable<PageResponseModel<RatingModel>>{
    return this.listService.all(`${this.baseApi}`, pagination)
  }

  filter(pagination: PageRequestModel, criteria: FilterRequestModel): Observable<PageResponseModel<RatingModel>>{
    return this.listService.filter(`${this.baseApi}/filter`, criteria, pagination)
  }

  getAvgScore(): Observable<number>{
    return this.http.get<ApiResponse<number>>(`${this.baseApi}/avg`)
      .pipe(map(resp => resp.data));
  }

  getFiveLatest(): Observable<RatingModel[]>{
    return this.http.get<ApiResponse<RatingModel[]>>(`${this.baseApi}/latest`)
      .pipe(
        map(resp => resp.data.map(rating => ({
          ...rating,
          date: new Date(rating.date)
        })))
      )
  }
}
