import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';
import { Router, RouterLink } from '@angular/router';
import { CustomerLoginServiceService } from '../../../../service/signup-service/customer-login-service.service';
import { CustomerLogin } from '../../../model/Customer';
import { AuthService } from '../../../../service/auth-service/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private customerLoginService: CustomerLoginServiceService, private router: Router, private authService: AuthService) { }
  txtEmail: string = '';
  txtPassword: string = '';

  loginButtonOnAction(): void {

    const customerLoginDetails: CustomerLogin = {
      email: this.txtEmail,
      password: this.txtPassword,
    }

    this.customerLoginService.userLogin(customerLoginDetails).then((response) => {

      if (response) {
        Swal.fire({
          icon: 'success',
          title: 'Login Successful',
          text: 'Welcome back!'
        });

        let role = this.authService.getRole();

        this.router.navigate(
          role === "CUSTOMER" ? ['/event'] : role == "SUPPLIER" ? ['/supplier/dashboard'] : ['/admin-dash']
        )
      }

    }).catch(error => {
        Swal.fire({
          icon: 'error',
          title: 'Login Failed',
          text: 'Invalid email or password. Please try again.'
        });
    })

  }

}
