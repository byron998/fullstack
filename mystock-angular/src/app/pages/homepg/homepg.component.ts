import { Component, OnInit } from '@angular/core';
import { ChartsComponent } from '../../component/charts/charts.component';

@Component({
  selector: 'app-homepg',
  templateUrl: './homepg.component.html',
  styleUrls: ['./homepg.component.css']
})
export class HomepgComponent implements OnInit {
  role : String;
  constructor() { }

  ngOnInit() {
    this.role = 'Admin'
  }

}
