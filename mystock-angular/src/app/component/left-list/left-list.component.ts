import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../services/product.service';
import { StockModel } from '../../models/stock.module'



const mockdata: StockModel[] = [{
    id: 1,
    code: '600062',
    price: 32.99,
    name: '中国平安'
    }, {
    id: 2,
    code: '638219',
    price: 149.99,
    name: '中国电信'
    }
];
@Component({
  selector: 'app-left-list',
  templateUrl: './left-list.component.html',
  styleUrls: ['./left-list.component.css']
})
export class LeftListComponent implements OnInit {

  constructor(private productService: ProductService) { }
  showFiller = false;
  butText = ">";
  products: StockModel[];
  ngOnInit() {
    // this.productService.allProducts().subscribe(data => {
    //   console.log(JSON.stringify(data));
    // })
    this.products = mockdata;
  }
  onchangeButton() {
    if(this.butText === ">") {
      this.butText = "<"
    }
    else if (this.butText === "<") {
      this.butText = ">"
    }

  }
}
