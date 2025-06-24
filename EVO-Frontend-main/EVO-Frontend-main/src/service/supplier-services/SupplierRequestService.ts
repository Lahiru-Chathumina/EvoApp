import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../app/environment/env.test";
import SupplierRequest from '../../app/model/SupplierRequest';
import Supplier from '../../app/model/supplier/Supplier';


@Injectable({
  providedIn: 'root'
})

export class SupplierRequestService {
    private baseUrl = `${environment.baseUrl}/supplier/request`;
  
    constructor(private http: HttpClient) { }
  
    saveSupplierRequest(supplierRequest: SupplierRequest): Observable<void> {
      return this.http.post<void>(`${this.baseUrl}/save`, supplierRequest);
    }
  
    getAll(): Observable<SupplierRequest[]> {
      return this.http.get<SupplierRequest[]>(`${this.baseUrl}/get-all`);
    }
  
    update(supplierRequest: SupplierRequest): Observable<void> {
      return this.http.put<void>(`${this.baseUrl}/update`, supplierRequest);
    }
  
    delete(id: number): Observable<void> {
      const params = new HttpParams().set('id', id.toString());
      return this.http.delete<void>(`${this.baseUrl}/delete`, { params });
    }
  
    searchById(id: number): Observable<SupplierRequest> {
      const params = new HttpParams().set('id', id.toString());
      return this.http.get<SupplierRequest>(`${this.baseUrl}/search`, { params });
    }
  
    addSupplierRequest(supplierRequest: SupplierRequest, supplierId: number): Observable<Supplier> {
      return this.http.post<Supplier>(`${this.baseUrl}/add/${supplierId}`, supplierRequest);
    }
  
    getSupplierRequestById(id: number): Observable<SupplierRequest> {
      return this.http.get<SupplierRequest>(`${this.baseUrl}/get/${id}`);
    }
  
    updateSupplierRequest(supplierRequest: SupplierRequest, supplierId: number): Observable<Supplier> {
      return this.http.put<Supplier>(`${this.baseUrl}/update/${supplierId}`, supplierRequest);
    }
  }
