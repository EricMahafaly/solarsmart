import { Injectable } from '@angular/core';
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BaseService {

  constructor() { }

  public createParams(params?: any){

    let httpParams = new HttpParams()
    if (params){
      let keys = Object.keys(params)
      for (let key of keys) {
        const val = params[key];
        if (val) httpParams = httpParams.set(key, params[key])
      }
    }

    // console.log("params = ", params)

    return httpParams;
  }

  public encode(id: string){

  }

  public decode(id: string){

  }
}
