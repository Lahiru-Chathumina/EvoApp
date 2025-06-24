import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../app/model/customer/User';
import { environment } from '../../app/environment/env.test';

@Injectable({
  providedIn: 'root'
})
export class SupplierUserService {

  private baseUrl = `${environment.baseUrl}/api/supplier`; 
  
  constructor(private http: HttpClient) { }

  addSupplierUser(user: User): Observable<User>{
    return this.http.post<User>(`${this.baseUrl}`,user);
  }

  getAllSuppliers(): Observable<User[]>{
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }

}
