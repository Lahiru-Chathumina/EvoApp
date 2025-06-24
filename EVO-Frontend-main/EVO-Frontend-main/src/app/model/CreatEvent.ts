export interface Location {
    locationId: number | null;
    city: string;
    village: string;
  }
  
  export interface Inventory {
    inventoryID: number | null;
    imageURL: string;
    description: string;
    supplierCategory: string;
  }
  
  export interface Supplier {
    id: number | null;
    businessName: string;
    businessContactNumber: string;
    businessEmail: string;
    description: string;
    availability: boolean;
    location: Location;
    profileImage: string | null;
    category: string;
    inventories: Inventory[];
  }
  
  export interface Venue {
    venueId: number | null;
    capacity: number;
    location: Location;
    venueType: string;
    properties: any[];
    requests: any[];
  }
  
  export interface AgendaTask {
    taskId: number;
    taskName: string;
    startTime: string; // ISO string
    endTime: string;   // ISO string
    supplierId: number;
    supplierType: string;
  }
  
  export interface Agenda {
    id: number | null;
    date: string; // YYYY-MM-DD
    time: string; // HH:mm
    tasks: AgendaTask[];
  }
  
  export interface Wedding {
    id: number | null;
    weddingType: string;
    date: string; // YYYY-MM-DD
  }
  
  export interface Event {
    id: number | null;
    userId: number;
    eventDate: string; // ISO string
    startTime: string; // HH:mm:ss
    endTime: string;   // HH:mm:ss
    venue: Venue;
    location: Location;
    eventType: string;
    capacity: number;
    budgetType: string;
    eventStatus: string;
    suppliers: Supplier[];
    agenda: Agenda;
    anniversary: any | null;
    wedding: Wedding | null;
    getTogether: any | null;
    birthdayParty: any | null;
  }
  