import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../app/environment/env.test';
import { Observable } from 'rxjs';
import Inventory from '../../app/model/Inventory';
import Supplier from '../../app/model/supplier/Supplier';

@Injectable({
  providedIn: 'root'
})

export class InventoryService {

  constructor(private http: HttpClient) {}


  baseUrl = `${environment.baseUrl}/api/supplier/`;

  // GET: /api/supplier/inventory
  getAllInventory(): Observable<Inventory[]> {
    return this.http.get<Inventory[]>(`${this.baseUrl}/inventory`);
  }

  // GET: /api/supplier/inventory/{id}
  searchInventoryById(id: string): Observable<Inventory> {
    return this.http.get<Inventory>(`${this.baseUrl}/inventory/${id}`);
  }

  // DELETE: /api/supplier/inventory/{id}
  deleteInventoryById(id: string): Observable<Boolean>{
    return this.http.delete<Boolean>(`${this.baseUrl}/inventory/${id}`);
  }

  // PUT: /api/supplier/{supplierID}/inventory
  updateInventory(supplierID: string, inventory: Inventory): Observable<Supplier> {
    return this.http.put<Supplier>(`${this.baseUrl}/${supplierID}/inventory`, inventory);
  }

  // POST: /api/supplier/{supplierID}/inventory
  addInventory(supplierID: string, inventory: Inventory): Observable<Supplier> {
    return this.http.post<Supplier>(`${this.baseUrl}/${supplierID}/inventory`, inventory);
  }

}
