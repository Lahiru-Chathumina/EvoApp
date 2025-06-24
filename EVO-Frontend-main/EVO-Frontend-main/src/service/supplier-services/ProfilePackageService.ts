import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../app/environment/env.test";
import ProfilePackage from '../../app/model/ProfilePackage';
import Supplier from '../../app/model/supplier/Supplier';


@Injectable({
  providedIn: 'root'
})
export class ProfilePackageService {
  private baseUrl = `${environment.baseUrl}/supplier/profile-package-controller`;

  constructor(private http: HttpClient) { }


  addProfilePackage(profilePackage: ProfilePackage, supplierID: number): Observable<Supplier> {
    return this.http.post<Supplier>(`${this.baseUrl}/supplier/{supplierID}`, profilePackage);
  }

  getAllProfilePackages(): Observable<ProfilePackage[]> {
    return this.http.get<ProfilePackage[]>(`${this.baseUrl}/supplier/profile-package`);
  }

  updateProfilePackage(profilePackage: ProfilePackage, supplierID: number): Observable<Supplier> {
    return this.http.put<Supplier>(`${this.baseUrl}/supplier/${supplierID}`, profilePackage);
  }

  deleteProfilePackage(packageId: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.baseUrl}/supplier/profile-package/${packageId}`);
  }

  searchProfilePackageSupplierByID(packageId: number): Observable<Supplier> {
    return this.http.get<Supplier>(`${this.baseUrl}/supplier/profile-package/${packageId}`);
  }

  searchProfilePackageSupplierByName(packageName: string): Observable<Supplier> {
    let params = new HttpParams().set('packageName', packageName);
    return this.http.get<Supplier>(`${this.baseUrl}/supplier/profile-package/search, ${packageName}`);
  }
}
