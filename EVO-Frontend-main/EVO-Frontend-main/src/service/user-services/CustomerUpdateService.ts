import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../app/environment/env.test';



export interface CustomerUpdateRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  contactNumber: string;
  profileImageUrl: string;
  address: string;
}

@Injectable({
  providedIn: 'root'
})
export class CustomerUpdateService {

  constructor(private http: HttpClient) {}

  updateCustomer(id: number, customer: CustomerUpdateRequest): Observable<any> {
    const url = `${environment.baseUrl}/customer/update/${id}`;
    return this.http.put(url, customer);
  }

  GetCustomer(id: number): Observable<any> {
    const url = `${environment.baseUrl}/customer/get/${id}`;
    return this.http.get(url);
  }

}
