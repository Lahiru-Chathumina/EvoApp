import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import CustomerPayment from '../../app/model/CustomerPayment';

@Injectable({
  providedIn: 'root',
})
export class CustomerReportService {
  constructor(private http: HttpClient) {}
  
  reportEvent(id:string): Observable<any> {
    return this.http.get(`http://localhost:8080/api/v1/reports/supplier/${id}`, { responseType: 'arraybuffer' }).pipe(
      map((response: ArrayBuffer) => {
      const blob = new Blob([response], { type: 'application/pdf' });
      return blob;
      })
    );
  }

  agenda(id: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/v1/reports/agenda/${id}`, { responseType: 'arraybuffer' }).pipe(
      map((response: ArrayBuffer) => {
      const blob = new Blob([response], { type: 'application/pdf' });
      return blob;
      })
    );
  }
 
}
