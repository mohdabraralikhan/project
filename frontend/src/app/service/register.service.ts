import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  constructor(private http:HttpClient){}

  registerUser(form:NgForm){
    return this.http.post('http://localhost:8080/register',{form});
  }}
