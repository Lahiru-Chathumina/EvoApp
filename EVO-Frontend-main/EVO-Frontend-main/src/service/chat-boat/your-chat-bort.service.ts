import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class YourChatBortService {

  private apikey = 'AIzaSyAwqfSFfWu1VRdlcXDrL3KJ8cVqVDE0Wfw';
  private apiUrl = 'https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=' + this.apikey;

  constructor(private http: HttpClient) { }


  sendMessage(message: string):Observable<any> {
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = {
      contents: [
        {
          parts: [
            { text: message }
          ]
        }
      ]
    };

    return this.http.post(this.apiUrl, body, { headers });
  }
}




