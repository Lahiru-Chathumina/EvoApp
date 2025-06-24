import { VenueType } from "../../../utils/VenueType";
import { Property } from "./Property";
import { VenueRequest } from "./VenueRequest";

export class Venue {
  venueId: number;
  capacity: number;
  location: Location;
  venueType: VenueType;
  properties: Property[];
  requests: VenueRequest[];

  constructor(
    venueId: number,
    capacity: number,
    location: Location,
    venueType: VenueType,
    properties: Property[],
    requests: VenueRequest[]
  ) {
    this.venueId = venueId;
    this.capacity = capacity;
    this.location = location;
    this.venueType = venueType;
    this.properties = properties;
    this.requests = requests;
  }
}
