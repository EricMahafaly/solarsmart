import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environment";
import {map, Observable} from "rxjs";
import {UserInfo} from "../../../models/api/info.model";
import {ApiResponse} from "../../../models/api/base/api.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseApi = `${environment.apiUrl}/admins`

  constructor(private http: HttpClient) { }

  getDetail(): Observable<UserInfo>{
    return this.http.get<ApiResponse<UserInfo>>(`${this.baseApi}/details`)
      .pipe(map(resp => resp.data))
  }
}
