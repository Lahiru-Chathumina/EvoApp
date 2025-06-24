import { Hall } from "./Hall";
import { OutdoorArea } from "./OutdoorArea";
import { Pool } from "./Pool";
import { PropertyImage } from "./PropertyImage";
import { Room } from "./Room";

export class Property {
  propertyId: number;
  name: string;
  hall: Hall;
  room: Room;
  pool: Pool;
  outdoorArea: OutdoorArea;
  images: PropertyImage[];

  constructor(
    propertyId: number,
    name: string,
    hall: Hall,
    room: Room,
    pool: Pool,
    outdoorArea: OutdoorArea,
    images: PropertyImage[]
  ) {
    this.propertyId = propertyId;
    this.name = name;
    this.hall = hall;
    this.room = room;
    this.pool = pool;
    this.outdoorArea = outdoorArea;
    this.images = images;
  }
}
