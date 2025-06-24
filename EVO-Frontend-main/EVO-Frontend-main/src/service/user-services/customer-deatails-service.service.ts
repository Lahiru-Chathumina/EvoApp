import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../app/environment/env.test';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerDeatailsServiceService {

  constructor(private http:HttpClient) { }


   private baseUrl = `${environment.baseUrl}/customer`;

  private baseUrlSupplier = `${environment.baseUrl}/api/supplier`;


  getAll():Observable<any>{
   return this.http.get(this.baseUrl+"/get-all")
  }

  getAllSupllier():Observable<any>{
    return this.http.get(this.baseUrlSupplier+"/get-all")
  }
  Delete(id:number):Observable<any>{
    return this.http.delete(this.baseUrl+"/delete/"+id)

  }

  deleteSuplier(id :number):Observable<any>{
    return this.http.delete(this.baseUrlSupplier+"/"+id)

  }
}


