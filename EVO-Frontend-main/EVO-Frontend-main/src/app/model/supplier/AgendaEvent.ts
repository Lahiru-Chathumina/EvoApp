export class TimelineEvent {
    date: string;
    time: string;
    description: string;
    title: string;

    constructor(date: string, time: string, title: string, description: string) {
        this.date = date;
        this.time = time;
        this.title = title;
        this.description = description;
    }
}

