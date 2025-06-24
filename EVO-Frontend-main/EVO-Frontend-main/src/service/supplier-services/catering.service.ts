import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../app/environment/env.test';
import { Catering } from '../../app/model/supplier/Catering';
import { Observable } from 'rxjs';
import Supplier from '../../app/model/supplier/Supplier';

@Injectable({
  providedIn: 'root'
})
export class CateringService {

  private baseUrl = `${environment.baseUrl}/api/supplier`;

  constructor(private http:HttpClient) { }
  
  getAllCatering(): Observable<Catering[]>{
    return this.http.get<Catering[]>(`${this.baseUrl}/catering`);
  }

  addCatering(catering:Catering, supplierID:number): Observable<Supplier>{
    return this.http.post<Supplier>(`${this.baseUrl}/${supplierID}/catering`,catering);
  }

  getCateringById(cateringID:number): Observable<Catering>{
    return this.http.get<Catering>(`${this.baseUrl}/catering/${cateringID}`);
  }

  updateCatering(catering: Catering, supplierID: number): Observable<Supplier> {
    return this.http.put<Supplier>(`${this.baseUrl}/${supplierID}/catering`, catering);
  }

  deleteCatering(supplierID: number): Observable<Boolean> {
    return this.http.delete<Boolean>(`${this.baseUrl}/${supplierID}/catering`);
  }

}



