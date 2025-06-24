import Supplier from "./Supplier";
import { Venue } from "./Venue";

export class VenueRequest {
  venueRequestID: number;
  supplier: Supplier;
  venue: Venue;
  createdDateTime: Date;
  status: boolean;

  constructor(
    venueRequestID: number,
    supplier: Supplier,
    venue: Venue,
    createdDateTime: string,
    status: boolean
  ) {
    this.venueRequestID = venueRequestID;
    this.supplier = supplier;
    this.venue = venue;
    this.createdDateTime = new Date(createdDateTime);
    this.status = status;
  }
}
