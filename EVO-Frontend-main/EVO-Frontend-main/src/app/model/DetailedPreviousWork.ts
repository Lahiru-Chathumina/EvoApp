
export default class OLD_DetailedPreviousWork {
    eventId: number;
    customerNames: string;
    eventDate: string;
    description: string;
    imageUrls: string[];

    constructor(eventId: number,customerNames: string,eventDate: string,description: string,imageUrls: string[] ) {
      this.eventId = eventId;
      this.customerNames = customerNames;
      this.eventDate = eventDate;
      this.description = description;
      this.imageUrls = imageUrls;
    }
  }
