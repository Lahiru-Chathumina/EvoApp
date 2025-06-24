import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../app/environment/env.test";
import { ProfilePreviousWork } from '../../app/model/supplier/ProfilePreviousWork';
import Supplier from '../../app/model/supplier/Supplier';

@Injectable({
  providedIn: 'root'
})

export class ProfilePreviousWorkService {
    private baseUrl = `${environment.baseUrl}/supplier`;
  
    constructor(private http: HttpClient) { }
  
    getAllProfilePreviousWork(): Observable<ProfilePreviousWork[]> {
      return this.http.get<ProfilePreviousWork[]>(`${this.baseUrl}/profile-previous-work`);
    }
  
    addProfilePreviousWork(profilePreviousWork: ProfilePreviousWork, supplierId: number): Observable<Supplier> {
      return this.http.post<Supplier>(`${this.baseUrl}/${supplierId}/profile-previous-work`, profilePreviousWork);
    }
  
    updateProfilePreviousWork(profilePreviousWork: ProfilePreviousWork, supplierId: number): Observable<Supplier> {
      return this.http.put<Supplier>(`${this.baseUrl}/${supplierId}/profile-previous-work`, profilePreviousWork);
    }
  
    deleteProfilePreviousWork(id: number): Observable<boolean> {
      return this.http.delete<boolean>(`${this.baseUrl}/profile-previous-work/${id}`);
    }
  
    searchProfilePreviousWorkByID(id: number): Observable<ProfilePreviousWork> {
      return this.http.get<ProfilePreviousWork>(`${this.baseUrl}/profile-previous-work/${id}`);
    }
  }