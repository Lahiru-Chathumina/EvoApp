import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../app/environment/env.test";

export class eventService{
   
 
    constructor(private http:HttpClient){}

    getAllEvents():Observable<any[]>{
        return this.http.get<any[]>(`${environment.baseUrl}/event/get-all`);

    }

    
    getEvent(id:any):Observable<any>{
        return this.http.get<any>(`${environment.baseUrl}/event/${id}`);
    }

    createEvent(event:any):Observable<any>{
        return this.http.post<any>(`${environment.baseUrl}/event`, event);
    }
}