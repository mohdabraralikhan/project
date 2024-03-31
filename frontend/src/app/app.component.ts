import {  Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegistrationComponent } from "./components/registration/registration.component";
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from "./components/login/login.component";
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';



@Component({
    selector: 'app-root',
    standalone: true,
    providers: [HttpClient],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, RegistrationComponent, HttpClientModule, LoginComponent,PageNotFoundComponent]
})
export class AppComponent {
  title = 'frontend';

  
}
