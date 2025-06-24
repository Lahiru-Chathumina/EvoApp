import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../app/environment/env.test";
import Supplier from '../../app/model/supplier/Supplier';

@Injectable({
  providedIn: 'root'
})

export class ProfileImageService {
    private baseUrl = `${environment.baseUrl}/supplier/profile-images`;
  
    constructor(private http: HttpClient) { }
  
    updateProfileImage(file: File, userId: number): Observable<any> {
      const formData = new FormData();
      formData.append('file', file);
      
      return this.http.post(`${this.baseUrl}/update/${userId}`, formData, {
        responseType: 'text'
      });
    }
  
    getProfileImage(userId: number): Observable<Blob> {
      return this.http.get(`${this.baseUrl}/get/${userId}`, {
        responseType: 'blob'
      });
    }
  
    deleteProfileImage(userId: number): Observable<any> {
      return this.http.delete(`${this.baseUrl}/delete/${userId}`, {
        responseType: 'text'
      });
    }
  
    
    changeProfilePicture(file: File, supplierId: number): Observable<Supplier> {
      const formData = new FormData();
      formData.append('file', file);
      
      return this.http.post<Supplier>(`${this.baseUrl}/change/${supplierId}`, formData);
    }
  
    addProfileImage(file: File, supplierId: number): Observable<Supplier> {
      const formData = new FormData();
      formData.append('file', file);
      
      return this.http.post<Supplier>(`${this.baseUrl}/add/${supplierId}`, formData);
    }
  
    getProfileImageById(profileImageId: number): Observable<Blob> {
      return this.http.get(`${this.baseUrl}/image/${profileImageId}`, {
        responseType: 'blob'
      });
    }
  
    createImageUrl(blob: Blob): string {
      return URL.createObjectURL(blob);
    }
  
    getProfileImageUrl(userId: number): Observable<HttpEvent<Blob>> {
      return this.http.request(new HttpRequest(
        'GET',
        `${this.baseUrl}/get/${userId}`,
        null,
        {
          responseType: 'blob',
          reportProgress: true
        }
      ));
    }
  }