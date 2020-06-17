import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import { UserModel } from '../models/user.module';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public get currentUserToken(): string {
    return sessionStorage.getItem('token');
  }

  postSignIn(UserModel) {
    return this.http.post(`${environment.baseUrl}/api/auth/login`, JSON.stringify(UserModel), httpOptions);
  }
  postRegister(UserModel) {
    return this.http.post(`${environment.baseUrl}/api/user/register`, JSON.stringify(UserModel), httpOptions);
  }
}
