
// StorageType
type StorageType = 'VENUE' | 'SUPPLIERS' | 'EVENT_FORM' | 'AGENDA'
type eventType = 'WEDDING' | 'BIRTHDAY_PARTY' | 'ANNIVERSARY' | 'GET_TOGETHER';

// VENUE
type VenueData = {
    id: number;
    name: string;
    about: string;
    location: string;
    price: number;
    capacity: number;
    image: string;
    type: string;
    supplierId: number;
};

// SUPPLIERS
type SupplierData = {
    id: string;
    businessName: string;
    description: string;
    features: FeatureData[];
    packages: PackageData[];
    rating: string;
};

type FeatureData = {
    id: number;
    feature: string;
    price: number;
};

type PackageData = {
    id: number;
    name: string;
    price: number;
    description: string;
};

// EVENT DETAILS
type EventFormData<T> = {
    eventType: eventType;
    headCount: number;
    startTime: string;
    endTime: string;
    eventDate: string;
    data: T
};


type WeddingFormData = {
    WeddingType: string;
    brideName: string;
    groomName: string;
};

type AnniversaryFormData = {
    year: number;
    wifeName: string;
    husbandName: string;
    startDate: string;
    description: string;
};

type GetTogetherFormData = {
    title: string;
    description: string;
};

type BirthdayPartyFormData = {
    ownerName: string;
};

// AGENDA
type AgendaData = {
    id: number;
    time: string;
    date: string;
    tasks: AgendaTask[];
    sessions: AgendaTask[];
}

type AgendaTask = {
    title: string;
    type: string;
    startTime: string;
    endTime: string;
    isEditing: boolean;
}

type StorageTypeMap = {
    VENUE: VenueData;
    SUPPLIERS: SupplierData[],
    AGENDA: AgendaData
    EVENT_FORM: EventFormData<EventFormTypeMap[eventType]>

};
type EventFormTypeMap  = {
    WEDDING: WeddingFormData,
    BIRTHDAY_PARTY: BirthdayPartyFormData,
    ANNIVERSARY: AnniversaryFormData,
    GET_TOGETHER: GetTogetherFormData
}


export const localStorageKeyMap = {
    VENUE: 'Venue',
    SUPPLIERS: 'suppliers',
    EVENT_FORM: 'FormData',
    AGENDA: 'savedAgendas',
}

export type {
    VenueData,
    SupplierData,
    EventFormData,
    FeatureData,
    PackageData,
    WeddingFormData,
    AnniversaryFormData,
    GetTogetherFormData,
    BirthdayPartyFormData,
    AgendaData,
    AgendaTask,
    eventType,
    StorageType,
    StorageTypeMap,
    EventFormTypeMap
}