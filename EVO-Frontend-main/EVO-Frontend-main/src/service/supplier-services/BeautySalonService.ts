import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../app/environment/env.test";
import { BeautySaloon } from "../../app/model/supplier/BeautySaloon";
import Supplier from "../../app/model/supplier/Supplier";

@Injectable({
    providedIn: "root"
})
export class BeautySalonService {
    private baseUrl = `${environment.baseUrl}/supplier/beauty-salon`;

    constructor(private http: HttpClient) {}

    getAllBeautySalon(): Observable<BeautySaloon[]> {
        return this.http.get<BeautySaloon[]>(`${this.baseUrl}/get-all`);
    }

    addBeautySalonSupplier(beautySaloon: BeautySaloon, supplierID: number): Observable<Supplier> {
        return this.http.post<Supplier>(`${this.baseUrl}/add?supplierID=${supplierID}`, beautySaloon);
    }

    deleteBeautySalonSupplier(supplierID: number): Observable<boolean> {
        return this.http.delete<boolean>(`${this.baseUrl}/delete/${supplierID}`);
    }

    updateBeautySalonSupplier(beautySaloon: BeautySaloon, supplierID: number): Observable<Supplier> {
        return this.http.put<Supplier>(`${this.baseUrl}/update?supplierID=${supplierID}`, beautySaloon);
    }

    getBeautySalon(salonID: number): Observable<BeautySaloon> {
        return this.http.get<BeautySaloon>(`${this.baseUrl}/get-id/${salonID}`);
    }
}
