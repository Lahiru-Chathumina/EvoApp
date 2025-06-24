export class AuditReport {

  reportId: number;
  // Event event; TODO: Unsure relationship
  timestamp: string;
  action: string;
  data: string;

  constructor(reportId: number, timestamp: string, action: string, data: string) {
    this.reportId = reportId;
    this.timestamp = timestamp;
    this.action = action;
    this.data = data;
  }
}
