import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  loginUser(username:string,password:string) {
    return this.http.post('http://localhost:8080/login',{username,password});

  }

  constructor(private http:HttpClient) { }
}
