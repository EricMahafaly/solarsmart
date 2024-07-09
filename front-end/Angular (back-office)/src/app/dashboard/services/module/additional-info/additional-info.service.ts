import { Injectable } from '@angular/core';
import {environment} from "../../../../environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiResponse} from "../../../../models/api/base/api.model";

@Injectable({
  providedIn: 'root'
})
export class AdditionalInfoService {

  private baseApi: string = `${environment.apiUrl}/modules/additional-info`

  constructor(private http: HttpClient) { }


  delete(id: number): Observable<ApiResponse<any>>{
    return this.http.delete<ApiResponse<any>>(`${this.baseApi}/${id}`);
  }

  deleteDetail(id: number): Observable<ApiResponse<any>>{
    return this.http.delete<ApiResponse<any>>(`${this.baseApi}/details/${id}`)
  }
}
