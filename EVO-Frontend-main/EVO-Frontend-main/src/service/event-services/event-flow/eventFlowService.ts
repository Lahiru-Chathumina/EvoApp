import { Injectable } from "@angular/core";
import { EventFormData, EventFormTypeMap, eventType, localStorageKeyMap, StorageType, StorageTypeMap, VenueData, WeddingFormData } from "./eventFlowLocalStorageTypes";
import { HttpClient } from "@angular/common/http";
import { forkJoin, Observable, switchMap } from "rxjs";
import { environment } from "../../../environment/env";
import Wedding from "../../../app/model/Wedding";
import { BudgetType } from "../../../utils/BudgetType";
import { EventStatusType } from "../../../utils/EventStatusType";
import { WeddingType } from "../../../utils/WeddingType";


@Injectable({
    providedIn: 'root'
})
export class EventFlowService {

    API_URLS: Record<StorageType, string> = {
        VENUE: `${environment.baseUrl}/api/`,
        AGENDA: '',
        EVENT_FORM: '',
        SUPPLIERS: '',
    }

    constructor(private http: HttpClient) { }

    dumpEventToDatabase() {

        // 1. Create an event summary
        // 2. Create suppliers for event summary
        // 3. Create agenda

        console.log("eventTYPE", this.getEventTypeFromLocalStorage())


        let savedEventSummaryID: number;

        this.createEventSummary().pipe(
            switchMap((eventSummary: any) => {
                savedEventSummaryID = eventSummary.id;
                return this.createSuppliersForSummary(savedEventSummaryID);
            }),

            switchMap(() => this.confirmEvent(savedEventSummaryID)),

            switchMap(() => this.createAgendaForEvent(savedEventSummaryID))

        ).subscribe({
            next: (result) => console.log('Event created successfully', result),
            error: (err) => console.error('Something went wrong', err)
        });



    }

    private createEventSummary() {
        const { headCount, data, ...commonData } = this.getObjectFromLocalStorage("EVENT_FORM") || {};

        const venueData = this.getObjectFromLocalStorage("VENUE");

        const source: any = {
            ...commonData,
            capacity: headCount,
            venueId: venueData?.id,
            budgetType: BudgetType.LOW,
            eventStatus: EventStatusType.SCHEDULED

        };


        switch (this.getEventTypeFromLocalStorage()) {

            case "WEDDING":
                const wedding = this.getObjectFromLocalStorage("EVENT_FORM", "WEDDING");

                console.log("wedding ", wedding);


                source["wedding"] = {
                    weddingType: wedding?.data.WeddingType,
                    ...wedding?.data,
                }

                break;

            case "ANNIVERSARY":
                const anniversary = this.getObjectFromLocalStorage("EVENT_FORM", "ANNIVERSARY");
                const { year, ...rest } = anniversary?.data || {};

                source["anniversary"] = {
                    anniversaryYear: year,
                    ...rest
                };

                break;

            case "BIRTHDAY_PARTY":
                const birthdayParty = this.getObjectFromLocalStorage("EVENT_FORM", "BIRTHDAY_PARTY");

                source["birthdayParty"] = {
                    ...birthdayParty?.data
                };

                break;

            case "GET_TOGETHER":
                const getTogether = this.getObjectFromLocalStorage("EVENT_FORM", "GET_TOGETHER")

                source["getTogether"] = {
                    ...getTogether?.data
                }

                break;

        }

        console.log("EVENT SUMMARY SOURCE", source);


        return this.http.post(`${environment.baseUrl}/event/summary`, source);


    }

    private createSuppliersForSummary(eventSummaryId: number) {
        let suppliers = this.getObjectFromLocalStorage("SUPPLIERS");

        return this.http.post(`${environment.baseUrl}/event/summary/supplier/${eventSummaryId}`, suppliers)
    }

    private createAgendaForEvent(eventSummaryID: number) {
        let { tasks, date, ...rest } = this.getObjectFromLocalStorage("AGENDA") || {};


        return this.http.post(`${environment.baseUrl}/event/agenda`,
            {
                ...rest,
                date,
                eventId: eventSummaryID,
                tasks: tasks?.map(task => ({
                    taskName: task.title,
                    startTime: this.toOffsetDateTime(date?.toString()!, task.startTime),
                    endTime: this.toOffsetDateTime(date?.toString()!, task.endTime),
                    supplierType: task.type.toUpperCase(),
                    supplierId: null
                }))

            }
        );
    }


    private confirmEvent(eventSummaryID: number) {
        return this.http.post(`${environment.baseUrl}/event/summary/confirm/${eventSummaryID}`, {});
    }

    private getEventTypeFromLocalStorage(): eventType | null {
        let item = localStorage.getItem(localStorageKeyMap["EVENT_FORM"]);

        if (!item) return null;

        return JSON.parse(item).eventType;
    }

    private getObjectFromLocalStorage<E extends eventType>(
        key: "EVENT_FORM",
        eventType: E

    ): EventFormData<EventFormTypeMap[E]> | null;

    private getObjectFromLocalStorage<E extends eventType>(
        key: "EVENT_FORM",
        eventType?: E

    ): StorageTypeMap["EVENT_FORM"] | null;

    private getObjectFromLocalStorage<K extends StorageType>(
        key: K,

    ): StorageTypeMap[K] | null;

    private getObjectFromLocalStorage<K extends StorageType, E extends eventType>(
        key: K,
        _eventType?: E

    ): EventFormData<EventFormTypeMap[E]> | StorageTypeMap[K] | null {
        const item = localStorage.getItem(localStorageKeyMap[key]);
        if (!item) return null;

        let { eventType,
            startTime,
            endTime,
            eventDate, ...rest } = JSON.parse(item);

        const parsed = {
            eventType,
            startTime,
            endTime,
            eventDate,
            data: rest
        }

        if (key === "EVENT_FORM" && _eventType) {
            return parsed as EventFormData<EventFormTypeMap[E]>;
        }

        return parsed as StorageTypeMap[K];
    }

    private toOffsetDateTime(date: string, time: string): string {
        const [hours, minutes] = time.split(':').map(Number);
        const dt = new Date(date);
        dt.setHours(hours, minutes, 0, 0);
        return dt.toISOString(); 
    }
} 