import { Injectable } from '@angular/core';
import {environment} from "../../../environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthResponseModel, UserModel} from "../../../models/api/auth-response.model";
import {LoginModel} from "../../../models/api/login.model";
import {RegisterModel} from "../../../models/api/register.model";
import {ApiResponse} from "../../../models/api/base/api.model";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl: string = `${environment.apiUrl}/auth/admin`

  constructor(private http: HttpClient, private routes: Router) { }

  loginPage(){
    this.routes.navigate(['sign-in'])
  }

  login(user: LoginModel): Observable<ApiResponse<AuthResponseModel>>{
    return this.http.post<ApiResponse<AuthResponseModel>>(`${this.baseUrl}/signin`, user)
  }

  register(user: RegisterModel): Observable<ApiResponse<AuthResponseModel>>{
    return this.http.post<ApiResponse<AuthResponseModel>>(`${this.baseUrl}/signup`, user)
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('access-token');
    return !!token;
  }

  signOut(): Observable<ApiResponse<any>>{
    const resp = this.http.get<ApiResponse<any>>(`${this.baseUrl}/logout`);
    return resp;
  }

  clearToken(){
    localStorage.removeItem('access-token');
    localStorage.removeItem('user')
  }

  storeToken(token: string): void{
    localStorage.setItem('access-token', token)
  }

  storeInformation(authModel: AuthResponseModel){
    this.storeToken(authModel.token)
    localStorage.setItem("user", JSON.stringify(authModel.user));
  }

  getInformation(): UserModel{
    let info: string | null = localStorage.getItem("user") ;

    let user: UserModel = {
      email: '',
      id: 0
    };

    if (info) user = JSON.parse(info) as UserModel;

    return user;
  }

  getToken() : string{
    let token : string = ''
    const tokenStore: string | null = localStorage.getItem('access-token');
    if (tokenStore) token = tokenStore;

    return token;
  }
}
