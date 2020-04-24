import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Route} from '@angular/router';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(private routeInfo: ActivatedRoute) { }

  name: string

  ngOnInit(): void {
    // console.log(JSON.stringify(this.route));
    this.name = this.routeInfo.snapshot.params['id'];
  }

}
