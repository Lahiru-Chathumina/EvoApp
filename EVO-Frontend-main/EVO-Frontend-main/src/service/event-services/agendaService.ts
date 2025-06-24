import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Session } from "@supabase/supabase-js";
import { AgendaComponent } from "../../app/page/event/agenda/agenda.component";
import { environment } from "../../app/environment/env.test";

@Injectable({
    providedIn: 'root'
})
export class AgendaService {
    
    constructor(private http:HttpClient) {
    }

    loadevent(id:number):Observable<Event[]>{
        return this.http.get<Event[]>(`${environment.baseUrl}/api/event/search/${id}`);
    }
    savesession(session:Session, agendaId:number):Observable<Session>{
        return this.http.post<Session>(`${environment.baseUrl}/api/event/agenda/${agendaId}/add-task`, {session});
    }
    loadsession(agendaId:number, taskId:number):Observable<Session[]>{
        return this.http.get<Session[]>(`${environment.baseUrl}/api/event/agenda/${agendaId}/task/${taskId}`);
    }
    deletesession(agendaId:number, taskId:number):Observable<Session>{
        return this.http.delete<Session>(`${environment.baseUrl}/api/event/agenda/${agendaId}/task/delete/${taskId}`);
    }
    updateagenda(agenda:AgendaComponent):Observable<AgendaComponent>{
        return this.http.put<AgendaComponent>(`${environment.baseUrl}/api/event/agenda/update`, {agenda});
    }

    addagenda(agenda:Agenda):Observable<Agenda>{
        return this.http.post<Agenda>(`${environment.baseUrl}/event/agenda/add`, {agenda});



    }

}


    interface Agenda{

        id:number;
        time:String;
        date:Date;
        tasks:String;
        
        
    }
    

    


