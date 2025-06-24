import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../../app/model/Customer';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerSignupService {
  private signupUrl = 'http://localhost:8080/api/auth/register-customer';

  constructor(private http: HttpClient) { }

  signup(customer: Customer): Observable<string> {
    // console.log(customer);

    return this.http.post(this.signupUrl, customer, { responseType: 'text' });
  }
}
