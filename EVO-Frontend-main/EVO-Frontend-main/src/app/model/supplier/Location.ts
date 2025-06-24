export class Location {
  locationId: number;
  city: string;
  village: string;

  constructor(locationId: number, city: string, village: string) {
    this.locationId = locationId;
    this.city = city;
    this.village = village;
  }
}
