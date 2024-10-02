import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './paginas/login/login.component';
import { SignUpComponent } from './paginas/signup/signup.component';
import { UserComponent } from './paginas/user/user.component';
import { AuthGuard } from './services/auth-guard.service';
export const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "signup",
    component: SignUpComponent
  },
  {
    path: "user",
    component: UserComponent,
    canActivate: [AuthGuard]
  }
];
