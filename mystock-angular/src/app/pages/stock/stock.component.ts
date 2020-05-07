import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Route} from '@angular/router';
import {StockModel} from '../../models/stock.module';

const mockdata: StockModel = {
  id: 1,
  code: '600062',
  price: 32.99,
  name: '中国平安',
  rate: 0.01,
  market: '01',
  industry: '001',
  count: 10000,
  trade_count: 10000,
  };

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})

export class StockComponent implements OnInit {

  constructor(private routeInfo: ActivatedRoute) { }

  stockid: string
  stockinfo: StockModel
  ngOnInit(): void {
    // console.log(JSON.stringify(this.route));
    // in same page id was not flesh
    //this.stockid = this.routeInfo.snapshot.params['id'];
    this.routeInfo.params.subscribe(
      params => {
        this.stockid = decodeURI(this.routeInfo.snapshot.paramMap.get('id'));
        if (Number(this.stockid) === mockdata.id) {
          this.stockinfo = mockdata;
        }
        else {
          
        }
      });
  }

}
