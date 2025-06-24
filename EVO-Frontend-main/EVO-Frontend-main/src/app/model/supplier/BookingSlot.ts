import { Property } from "./Property";

export class BookingSlot {
  bookingSlotId: number;
  property: Property;
  startTime: string;
  endTime: string;

  constructor(
    bookingSlotId: number,
    property: Property,
    startTime: string,
    endTime: string
  ) {
    this.bookingSlotId = bookingSlotId;
    this.property = property;
    this.startTime = startTime;
    this.endTime = endTime;
  }
}
