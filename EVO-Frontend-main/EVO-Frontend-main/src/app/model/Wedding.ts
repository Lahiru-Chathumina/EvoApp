import { WeddingType } from "../../utils/WeddingType";

export default class Wedding {
    eventId: string;
    eventSummaryId: string;
    weddingType: WeddingType;

    constructor(eventId: string, eventSummaryId: string, weddingType: WeddingType) {
        this.eventId = eventId;
        this.eventSummaryId = eventSummaryId;
        this.weddingType = weddingType;
    }
}