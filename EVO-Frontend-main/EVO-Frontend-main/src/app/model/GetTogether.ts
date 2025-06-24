export default class GetTogether {
    eventId: string;
    eventSummaryId: string;
    description: string;
    title: string;

    constructor(eventId: string, eventSummaryId: string, description: string,title: string){
        this.eventId = eventId;
        this.eventSummaryId = eventSummaryId;
        this.description = description;
        this.title = title;
    }
}