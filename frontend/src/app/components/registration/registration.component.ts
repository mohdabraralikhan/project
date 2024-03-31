import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule, NgForm } from '@angular/forms';
import { RegisterService } from '../../service/register.service';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [FormsModule],
  providers:[RegisterService],
  templateUrl: './registration.component.html'})
  
export class RegistrationComponent {
  constructor(private http:HttpClient,private registerService:RegisterService){}
  onSubmit(form: NgForm) {
    if (form.valid) {
      console.log(form.value);
      this.registerService.registerUser(form.value);
    }
  }
  
}
  