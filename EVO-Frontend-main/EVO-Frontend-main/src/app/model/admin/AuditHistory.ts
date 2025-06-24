export class AuditHistory {
  id: number;
  reasonForChange: string;
  timestamp: Date;
  actionType: string;
  affectedEntity: string;
  newData: string;
  oldData: string;

  constructor(
    id: number,
    reasonForChange: string,
    timestamp: Date,
    actionType: string,
    affectedEntity: string,
    newData: string,
    oldData: string
  ) {
    this.id = id;
    this.reasonForChange = reasonForChange;
    this.timestamp = timestamp;
    this.actionType = actionType;
    this.affectedEntity = affectedEntity;
    this.newData = newData;
    this.oldData = oldData;
  }
}
