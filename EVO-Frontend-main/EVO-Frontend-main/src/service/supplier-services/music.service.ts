import { Injectable } from '@angular/core';
import { environment } from '../../app/environment/env.test';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Music } from '../../app/model/supplier/Music';
import Supplier from '../../app/model/supplier/Supplier';

@Injectable({
  providedIn: 'root'
})
export class MusicService {

  private baseUrl = `${environment.baseUrl}/api/supplier`;

  constructor(private http: HttpClient) { }

  getAllMusic():Observable<Music[]>{
    return this.http.get<Music[]>(`${this.baseUrl}/music`)
  }
  addMusicSupplier(music: Music, supplierID: number): Observable<Supplier> {
    return this.http.post<Supplier>(`${this.baseUrl}/${supplierID}/music`, music);
  }
  getMusicByID(musicID: number): Observable<Music> {
    return this.http.get<Music>(`${this.baseUrl}/music/${musicID}`);
  }
  updateMusicSupplier(music: Music, supplierID: number): Observable<Supplier> {
    return this.http.put<Supplier>(`${this.baseUrl}/${supplierID}/music`, music);
  }
  deleteMusicSupplier(supplierID: number): Observable<Boolean> {
    return this.http.delete<Boolean>(`${this.baseUrl}/${supplierID}/music`);
  }
}
