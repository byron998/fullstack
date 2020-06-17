import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import { Router } from '@angular/router';

interface Alert {
  type: string;
  message: string;
}

const ALERTS: Alert[] = [];

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})


export class SignUpComponent implements OnInit {
  alerts: Alert[];
  constructor(private userService: UserService, private router: Router) {
    this.reset();
  }

  ngOnInit() {
    
  }
  onSubmit(value: any) {
    if (this.validInput(value)) {
      this.userService.postRegister(value).subscribe(
        data => {
          console.log(JSON.stringify(data));
          const info: any = data;
          if (200 === info.code) {
              console.log('注册成功，弹出MSG确定后调转登陆页');
              this.router.navigate(['/sign-in']);
          } else {
            console.log('注册失败，弹出MSG');
            this.alerts.push({type : 'danger', message: 'User register failed!'});
          }
        }
      );
    }
  }
  validInput(value: any): boolean {
    this.reset();
    let result = true
    if (!value.username) {
      this.alerts.push({type : 'danger', message: 'username required!'});
      result = false;
    }

    if (!value.modile) {
      this.alerts.push({type : 'danger', message: 'modile required!'});
      result =  false;
    }

    if (!value.password) {
      this.alerts.push({type : 'danger', message: 'password required!'});
      result =  false;
    }

    if (!value.repassword) {
      this.alerts.push({type : 'danger', message: 'repassword required!'});
      result =  false;
    }

    if (value.password.length < 6) {
      this.alerts.push({type : 'danger', message: 'password length must be greater than 6!'});
      result =  false;
    }

    if (value.password != value.repassword) {
      this.alerts.push({type : 'danger', message: 'password and confirm password not same!'});
      result =  false;
    }
    return result;
  }

  close(alert: Alert) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }

  reset() {
    this.alerts = Array.from(ALERTS);
  }
}
