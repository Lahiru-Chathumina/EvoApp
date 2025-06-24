import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Meal } from "../../app/model/supplier/Meal";
import { environment } from "../../app/environment/env.test";

@Injectable({
    providedIn: 'root'
})
export class MealService {
    constructor(private http:HttpClient) {       
    }

    baseUrl = `${environment.baseUrl}/api/supplier`;

    loadAllMeals(): Observable<Meal[]> {
        return this.http.get<Meal[]>(`${this.baseUrl}/meal`);
    }
    saveMeal(meal: Meal,cateringID:number): Observable<Meal> {
        return this.http.post<Meal>(`${this.baseUrl}/${cateringID}/meal`, meal);
    }
    deleteMeal(mealID:number): Observable<Meal> {
        return this.http.delete<Meal>(`${this.baseUrl}/meal/${mealID}`);
    }
    updateMeal(meal: Meal,cateringID:number): Observable<Meal> {
        return this.http.put<Meal>(`${this.baseUrl}/${cateringID}/meal`, meal);
    }
    searchMealByType(mealType: string): Observable<Meal[]> {
        return this.http.get<Meal[]>(`${this.baseUrl}/meal/catering/search?mealType=${mealType}`);
    }
    searchMealByName(mealName: string): Observable<Meal[]> {
        return this.http.get<Meal[]>(`${this.baseUrl}/meal/catering/search?mealName=${mealName}`);
    }
    searchMealById(mealID:number): Observable<Meal[]> {
        return this.http.get<Meal[]>(`${this.baseUrl}/meal/${mealID}`);
    }
}