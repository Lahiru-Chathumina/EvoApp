import { HttpClient } from "@angular/common/http";
import Supplier from "../../app/model/supplier/Supplier";
import { Observable } from "rxjs";
import { SupplierRequest } from "../../app/model/supplier/SupplierRequest";
import { Injectable } from "@angular/core";
import { environment } from "../../app/environment/env.test";

@Injectable({
    providedIn:"root"
})
export class SupplerRequestService{
    constructor(private http:HttpClient){

    }


    addSupplierRequest(supplierID:number,supplierRequest:SupplierRequest):Observable<Supplier>{
       return this.http.post<Supplier>(`${environment.baseUrl}api/supplier/${supplierID}/request`,supplierRequest)
    }

    getAllSupplierRequests():Observable<SupplierRequest[]>{
        return this.http.get<SupplierRequest []>(`${environment.baseUrl}/api/supplier/request`)
    }

    updateSupplierRequest(supplierID:number,supplierRequest:SupplierRequest):Observable<Supplier>{
        return this.http.put<Supplier>(`${environment.baseUrl}/api/supplier/${supplierID}/request`,supplierRequest)
    }






    
}