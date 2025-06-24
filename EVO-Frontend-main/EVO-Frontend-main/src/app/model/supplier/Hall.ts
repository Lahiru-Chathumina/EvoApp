import { HallAvailabilityType } from "../../../utils/HallAvailabilityType";

export class Hall {
  hallId: number | null;
  count: number;
  availability: HallAvailabilityType;

  constructor(hallId: number | null = null, count: number = 0, availability: HallAvailabilityType) {
    this.hallId = hallId;
    this.count = count;
    this.availability = availability;
  }
}
