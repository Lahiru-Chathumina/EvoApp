export class Room {
  roomId: number | null;
  beds: number;

  constructor(roomId: number | null = null, beds: number = 0) {
    this.roomId = roomId;
    this.beds = beds;
  }
}
