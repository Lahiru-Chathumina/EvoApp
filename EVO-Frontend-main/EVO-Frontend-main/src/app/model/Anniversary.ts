export default class {
    eventId: string;
    eventSummaryId: string;
    anniversaryYear: string;
    wifeName: string;
    husbandName: string;
    description: string;

    constructor(eventSummaryId: string, eventId: string, evetSummaryId: string, anniversaryYear: string, wifeName: string, husbandName: string,
        description: string
    ) {
        this.anniversaryYear = anniversaryYear;
        this.description = description;
        this.eventId = eventId;
        this.eventSummaryId = eventSummaryId;
        this.wifeName = wifeName;
        this.husbandName = husbandName;
    }
}