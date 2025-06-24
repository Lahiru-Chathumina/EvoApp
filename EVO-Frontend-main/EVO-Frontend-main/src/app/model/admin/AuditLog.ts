import { AuditActionType } from "../../utils/AuditActionType";

export class AuditLog {
  logId: number;
  timestamp: string;
  type: AuditActionType;

  constructor(logId: number, timestamp: string, type: AuditActionType) {
    this.logId = logId;
    this.timestamp = timestamp;
    this.type = type;
  }
}
