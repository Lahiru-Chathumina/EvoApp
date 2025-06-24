import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { param } from 'jquery';

@Injectable({
  providedIn: 'root',
})
export class ContactService {

  // Contact succesfull added but error msg throw...but google sheet contact saved....

  private scriptUrl = 'https://script.google.com/macros/s/AKfycbyiMFoDyoS8xy-OfcVS3Q4oxPf5jjUmavLVo2kNIm5xSUZxIYrtC0cyrgItQxw5jJB99w/exec';

  constructor(private http: HttpClient) {}

  addContact(name: string, email: string, message: string): Observable<any> { 

    const params = new URLSearchParams();
    params.append('Name',name);
    params.append('Email',email);
    params.append('Message',message);

    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'
      })
    };

    console.log(params.toString());
    

    return this.http.post(this.scriptUrl, params.toString(),options);
  }
}
