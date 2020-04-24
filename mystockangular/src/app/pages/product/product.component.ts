import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../services/product.service';

interface ProductItem {
  id: string;
  price: number
  title: string;
  desc: string;
  pic: string;
}

const PRODUCTS: ProductItem[] = [{
    id: '1',
    price: 3299,
    title: 'iphone se',
    desc: 'iPhone SE packs A13 Bionic, Portrait mode, 4K video, Touch ID, a Retina HD display, and great battery life into a 4.7” design',
    pic: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587214615893&di=941488bce9f57493a71cb29380878ec8&imgtype=0&src=http%3A%2F%2Fimg0.pconline.com.cn%2Fpconline%2F2004%2F16%2Fg_13363665_1587023070013.jpg'
  }, {
    id: '2',
    price: 4999,
    title: 'xiaomi 10',
    desc: 'new xiaomi phone',
    pic: 'http://img4.imgtn.bdimg.com/it/u=2045085489,1540238884&fm=214&gp=0.jpg'
  }
];

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private productService: ProductService) { }

  products: ProductItem[];

  ngOnInit(): void {
    this.productService.allProducts().subscribe(data => {
      console.log(JSON.stringify(data));
    })
    this.products = PRODUCTS;
  }

}
