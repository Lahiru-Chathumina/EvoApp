import { AgendaTask } from "./AgendaTask";

export class Agenda {
    id: number;
    email: string;
    eventDate: string;
    tasks: AgendaTask[];

    constructor(id: number, email: string, eventDate: string, tasks: AgendaTask[]) {
        this.id = id;
        this.email = email;
        this.eventDate = eventDate;
        this.tasks = tasks;
    }
}

