import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';

import { PriceFormat } from '../model/priceformat'

const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin':  '*'
  })
}

@Injectable({
  providedIn: 'root'
})
export class PriceFormatterService {

  constructor(private http:HttpClient) { }

  public async process(input:PriceFormat){
    return await this.http.post('http://localhost:8080/format-price',input,httpOptions).toPromise();
  }
}
