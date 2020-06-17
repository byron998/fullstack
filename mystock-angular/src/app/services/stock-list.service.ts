import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class StockListService {

  constructor(private http: HttpClient) { }

  allStocks() {
    return this.http.get(`${environment.baseUrl}/api/stock/all`, httpOptions);
  }

}



