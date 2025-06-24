import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../app/environment/env.test";
import { UpperCasePipe } from "@angular/common";


@Injectable({
    providedIn:"root"
})

export class VenueService{
    constructor(private http:HttpClient){}

    getAllVenues(type:string):Observable<any[]>{
        return this.http.get<any[]>(`${environment.baseUrl}/api/supplier/venue`);
    }

    getVenues(id:string):Observable<any>{
        return this.http.get<any[]>(`${environment.baseUrl}api/supplier/venue/${id}`);
    }
}