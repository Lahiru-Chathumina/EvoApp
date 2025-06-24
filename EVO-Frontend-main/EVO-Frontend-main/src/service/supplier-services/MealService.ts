import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../app/environment/env.test";
import { Meal } from '../../app/model/supplier/Meal';
import { MealType } from '../../utils/MealType';
import { Catering } from '../../app/model/supplier/Catering';

@Injectable({
  providedIn: 'root'
})

export class MealService {
  private baseUrl = `${environment.baseUrl}/supplier/meal-controller`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Meal[]> {
    return this.http.get<Meal[]>(`${this.baseUrl}/get-all`);
  }

  searchByName(name: string): Observable<Meal[]> {
    return this.http.get<Meal[]>(`${this.baseUrl}/search/${name}`);
  }

  searchById(id: number): Observable<Meal> {
    return this.http.get<Meal>(`${this.baseUrl}/search/${id}`);
  }

  searchByType(type: MealType): Observable<Meal[]> {
    return this.http.get<Meal[]>(`${this.baseUrl}/search/${type}`);
  }

  save(meal: Meal): Observable<Meal> {
    return this.http.post<Meal>(`${this.baseUrl}/save`, meal);
  }

  delete(meal: Meal): Observable<boolean> {
    return this.http.delete<boolean>(`${this.baseUrl}/delete`, { body: meal });
  }

  deleteById(id: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.baseUrl}/delete/${id}`);
  }

  update(meal: Meal): Observable<Meal> {
    return this.http.put<Meal>(`${this.baseUrl}/update`, meal);
  }

  getAllCateringMeals(): Observable<Meal[]> {
    return this.http.get<Meal[]>(`${this.baseUrl}/catering/get-all`);
  }

  addCateringMeal(meal: Meal, cateringId: number): Observable<Catering> {
    return this.http.post<Catering>(`${this.baseUrl}/catering/${cateringId}`, meal);
  }

  deleteCateringMeal(id: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.baseUrl}/catering/meal/${id}`);
  }

  updateCateringMeal(meal: Meal, cateringId: number): Observable<Catering> {
    return this.http.put<Catering>(`${this.baseUrl}/catering/${cateringId}`, meal);
  }

  searchCateringByMealType(type: MealType): Observable<Catering[]> {
    return this.http.get<Catering[]>(`${this.baseUrl}/catering/search/type/${type}`);
  }

  searchCateringByMealName(name: string): Observable<Catering[]> {
    return this.http.get<Catering[]>(`${this.baseUrl}/catering/search/name/${name}`);
  }

  searchMealById(id: number): Observable<Meal> {
    return this.http.get<Meal>(`${this.baseUrl}/search/${id}`);
  }
}