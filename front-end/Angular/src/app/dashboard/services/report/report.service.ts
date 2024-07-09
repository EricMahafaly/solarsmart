import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ListService} from "../core/list.service";
import {
  ReportComment,
  ReportCommentRequest,
  ReportDetail,
  ReportModel,
  ReportStateModel
} from "../../../models/api/report.model";
import {environment} from "../../../environment";
import {PageRequestModel} from "../../../models/api/base/page-request.model";
import {map, Observable} from "rxjs";
import {PageResponseModel} from "../../../models/api/base/page-response.model";
import {AuthService} from "../authentication/auth.service";
import {UserModel} from "../../../models/api/auth-response.model";
import {ApiResponse} from "../../../models/api/base/api.model";
import {FilterRequestModel} from "../../../models/api/base/filter.model";
import {CustomerInfoModel} from "../../../models/api/customer-info.model";

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  private baseApi: string = `${environment.apiUrl}/reports`

  constructor(
    private http: HttpClient,
    private listService: ListService<ReportModel>,
    private authService: AuthService) { }

  getAll(pagination : PageRequestModel): Observable<PageResponseModel<ReportModel>>{
    const userInfo: UserModel = this.authService.getInformation();
    let url = `${this.baseApi}/admin/${userInfo.id}`

    return this.listService.all(url, pagination).pipe(
      map((resp: PageResponseModel<ReportModel>) => ({
        ...resp,
        items: resp.items.map(report => ({
          ...report,
          createdDate: new Date(report.createdDate),
          closedDate: new Date(report.closedDate),
        }))
      }) as PageResponseModel<ReportModel>)
    );
  }

  filter(pagination: PageRequestModel, criteria: FilterRequestModel[]): Observable<PageResponseModel<ReportModel>>{
    return this.listService.filter(`${this.baseApi}/filter`, criteria, pagination);
  }

  getAllCommentsByReport(reportId: number): Observable<ReportDetail>{
    const url: string = `${this.baseApi}/${reportId}/comments`;

    return this.http.get<ApiResponse<ReportDetail>>(url).pipe(
      map(resp => ({
        ...resp.data,
        comments: resp.data.comments.map(comment => ({
          ...comment,
          date: new Date(comment.date)
        }))
      }))
    )
  }

  closeReport(reportId: number): Observable<ApiResponse<any>>{
    return this.http.get<ApiResponse<any>>(`${this.baseApi}/${reportId}/close`);
  }

  getAllReportState(): Observable<ReportStateModel[]>{
    const url: string = `${this.baseApi}/states`;

    let response = this.http.get<ApiResponse<ReportStateModel[]>>(url);

    // console.log("states = ", response)

    return response.pipe(
      map(resp => resp.data))
  }

  commentReport(reportId: number ,commentRequest: ReportCommentRequest): Observable<ApiResponse<any>>{
    const url: string = `${this.baseApi}/${reportId}/comments`;

    return this.http.post<ApiResponse<any>>(url, commentRequest);
  }

  getUnsolvedReportCount(): Observable<number>{
    return this.http.get<ApiResponse<number>>(`${this.baseApi}/unsolved/count`)
      .pipe(map(resp => resp.data))
  }
}
