import { Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { ListUsersComponent } from './pages/list-users/list-users.component';

export const routes: Routes = [
  {
    path:"register",
    component: RegisterComponent
  },
  {
    path:"list",
    component:ListUsersComponent
  }
];
