export default class BirthdayParty {
    eventId: string;
    eventSummaryId: string;
    ownerName: string;

    constructor(eventId: string, eventSummaryId: string, ownerName: string){
        this.eventId =eventId;
        this.eventSummaryId = eventSummaryId;
        this.ownerName = ownerName;
    }
}