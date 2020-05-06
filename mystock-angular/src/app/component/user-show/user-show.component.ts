import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-show',
  templateUrl: './user-show.component.html',
  styleUrls: ['./user-show.component.css']
})
export class UserShowComponent implements OnInit {

  constructor() { }
  userrole = ''
  ngOnInit() {
    this.userrole = localStorage.getItem('userrole');
  }

}
