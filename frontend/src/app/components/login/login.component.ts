import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { LoginService } from '../../service/login.service';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  providers:[LoginService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private http:HttpClient,private loginService:LoginService){}
  onSubmit(form: NgForm) {
    if (form.valid) {
      console.log(form.value);
      const username:string = form.value.username;
      const password:string = form.value.password;
      
      this.loginService.loginUser(username,password).subscribe(response => console.log(response));
    }
  }
}

