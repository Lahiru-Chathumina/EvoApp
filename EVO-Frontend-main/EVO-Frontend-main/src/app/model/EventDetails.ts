export default class Adgenda {
    eventType: string;
    venue: string;
    date: Date;
    organizer: string;

    constructor(eventType: string, venue: string, date: Date, organizer: string) {
        this.eventType=eventType;
        this.venue=venue;
        this.date=date;
        this.organizer=organizer;
    }
}