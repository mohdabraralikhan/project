import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { CreateCampaignComponent } from './components/create-campaign/create-campaign.component';
import { CampaignsComponent } from './components/campaigns/campaigns.component';

export const routes: Routes = [
    {path:'',redirectTo:'home',pathMatch:'full'},
    {path:'home',component:HomeComponent},
    {path:'login',component:LoginComponent},
    {path:'signup',component:RegistrationComponent},
    {path:'start-campaign',component:CreateCampaignComponent},
    {path:'campaigns',component:CampaignsComponent},
    {path:'**',redirectTo:'404'},
    {path:'404',component:PageNotFoundComponent}
];
