import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { forkJoin, lastValueFrom, Observable } from 'rxjs';
import { environment } from '../../app/environment/env.test';
import Supplier from '../../app/model/supplier';
import { Venue } from '../../app/model/supplier/Venue';
import OLD_Venue from '../../app/model/Venue';
import { map } from 'rxjs/operators';
import OLD_PhotographerExtraFeature from '../../app/model/PhotographerExtraFeature';
import OLD_PhotographerPackage from '../../app/model/PhotographerPackage';
import OLD_PhotographerReview from '../../app/model/PhotographerReview';

@Injectable({
    providedIn: 'root'
})
export class SupplierService {

    private apiUrl = `${environment.baseUrl}/api/supplier`;
    private allSuppliers: Supplier[] = [];

    constructor(private http: HttpClient) { }



    getAllSuppliers(): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}`)

    }

    searchSupplier(id: string): Observable<Supplier> {
        return this.http.get<Supplier>(`${this.apiUrl}/${id}`);
    }


    supplierReviews(id: string): Observable<OLD_PhotographerReview[]> {
        return this.http.get<OLD_PhotographerReview[]>(`${environment.baseUrl}/review/SearchBySupplierId/${id}`);
    }


    supplierFetures(id: string): Observable<OLD_PhotographerExtraFeature[]> {
        //replace correct url
        return this.http.get<OLD_PhotographerExtraFeature[]>(`${environment.baseUrl}/supplier-feature/${id}`);
    }

    
    supplierPackages(id: string): Observable<OLD_PhotographerPackage[]> {
        //replace correct url
        return this.http.get<OLD_PhotographerPackage[]>(`${environment.baseUrl}/supplier-package/${id}`);
    }
    

    updateSupplier(supplier: Supplier): Observable<Supplier> {
        return this.http.put<Supplier>(`${this.apiUrl}/update`, supplier);
    }


    deleteSupplier(supplierID: number): Observable<boolean> {
        return this.http.delete<boolean>(`${this.apiUrl}/delete/${supplierID}`);
    }
}
