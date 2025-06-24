import { end } from "@popperjs/core";
import { BudgetType } from "../../utils/BudgetType";
import { EventStatusType } from "../../utils/EventStatusType";
import { EventType } from "../../utils/EventType";
import Anniversary from "./Anniversary";
import BirthdayParty from "./BirthdayParty";
import GetTogether from "./GetTogether";
import Wedding from "./Wedding";

export default class EventModelNew {
    id: string;
    userId: string;
    venueId: string;
    location: string;
    eventDate: Date;
    startTime: string;
    endTime: string;
    eventType: EventType;
    headCount: number;
    totalPrice: number;
    budgetType: string;
    eventStatus: EventStatusType;
    anniversary: Anniversary;    
    birthdayParty: BirthdayParty;
    getTogether: GetTogether;
    wedding: Wedding;

    constructor(id: string, userId: string, venueId: string,location: string,eventDate:Date,startTime:string,endTime:string,eventType:EventType,headCount:number,totalPrice:number,budgetType:BudgetType,eventStatus:EventStatusType,anniversary:Anniversary,birthdayParty:BirthdayParty,getTogether:GetTogether,wedding:Wedding){
        this.anniversary = anniversary,
        this.birthdayParty = birthdayParty,
        this.budgetType = budgetType,
        this.endTime = endTime,
        this.eventDate = eventDate,
        this.eventStatus = eventStatus,
        this.eventType = eventType,
        this.getTogether = getTogether,
        this.headCount = headCount,
        this.id = id,
        this.location = location,
        this.startTime = startTime,
        this.totalPrice = totalPrice,
        this.userId = userId,
        this.venueId = venueId,
        this.wedding = wedding
    }
}