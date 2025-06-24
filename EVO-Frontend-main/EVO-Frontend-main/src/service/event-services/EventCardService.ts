import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../app/environment/env.test";

    @Injectable({
        providedIn:"root"
    })

export class EventCardService{
    
    constructor(private http:HttpClient){
    }

    getAllEvents():Observable<Event[]>{
        return this.http.get<Event[]>(`${environment.baseUrl}/event/all`);
    }
}
    