import OLD_Supplier from "./supplier";

export default class OLD_EventSummery {
    eventSummeryId: number;
    eventId: number;
    venueId: number;
    customerId: number;
    suppilerList: OLD_Supplier[];
    total: number;

    constructor(
        eventSummeryId: number,
        eventId: number,
        venueId: number,
        customerId: number,
        suppilerList: [],
        total: number
    ) {
        this.eventSummeryId = eventSummeryId;
        this.eventId = eventId;
        this.venueId = venueId;
        this.customerId = customerId;
        this.suppilerList = suppilerList;
        this.total = total;
    }


}
