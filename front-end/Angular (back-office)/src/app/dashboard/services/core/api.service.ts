import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../environment";
import {Observable} from "rxjs";
import {BaseService} from "./base.service";

@Injectable({
  providedIn: 'root'
})
export class ApiService<T> {

  private baseUrl = environment.apiUrl;

  constructor(private httpClient: HttpClient, private baseService: BaseService) { }

  public get(url: string, params?: any): Observable<T>{
    return this.httpClient.get<T>(`${url}`, {params: this.createParams(params)})
  }

  public post(url: string, data: any, params?: any): Observable<T>{
    return this.httpClient.post<T>(url, data, {params: this.createParams(params)})
  }

  // prepareUrl(url: string){
  //   return `${this.baseUrl}${url}`;
  // }

  public createParams(params?: any){
    return this.baseService.createParams(params);
  }
}
