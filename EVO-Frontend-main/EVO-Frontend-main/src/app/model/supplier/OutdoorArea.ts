export class OutdoorArea {
  id: number;
  name: string;
  size: number;
  seatingCapacity: boolean;
  lighting: boolean;
  weatherProtection: boolean;

  constructor(
    id: number,
    name: string,
    size: number,
    seatingCapacity: boolean,
    lighting: boolean,
    weatherProtection: boolean
  ) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.seatingCapacity = seatingCapacity;
    this.lighting = lighting;
    this.weatherProtection = weatherProtection;
  }
}
