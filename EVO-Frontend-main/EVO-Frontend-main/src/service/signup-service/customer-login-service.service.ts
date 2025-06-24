import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerLogin } from '../../app/model/Customer';
import { Observable } from 'rxjs';
import { AuthService } from '../auth-service/auth.service';
import { environment } from '../../app/environment/env.test';

@Injectable({
  providedIn: 'root'
})
export class CustomerLoginServiceService {
  private loginUrl: string = `${environment.baseUrl}/api/auth/login`;


  constructor(private http: HttpClient, private authService: AuthService) { }

  userLogin(customerLogin: CustomerLogin) : Promise<any> {
    return new Promise((resolve, reject) => {
      this.http.post<{ token: string }>(this.loginUrl, customerLogin).subscribe({
        next: (response) => {
          this.authService.setToken(response.token);
          resolve(response);
        },
        error: (error) => {
          reject(error);
        }
      });
    });

  }
}
