export class Report {
  reportId: number;
  title: string;
  description: string;
  dateCreate: Date;
  dateModify: Date;
  reportType: any;
  auditReport: any;
  userReport: any;
  eventReport: any;

  constructor(
    reportId: number,
    title: string,
    description: string,
    dateCreate: Date,
    dateModify: Date,
    reportType: any,
    auditReport: any,
    userReport: any,
    eventReport: any
  ) {
    this.reportId = reportId;
    this.title = title;
    this.description = description;
    this.dateCreate = dateCreate;
    this.dateModify = dateModify;
    this.reportType = reportType;
    this.auditReport = auditReport;
    this.userReport = userReport;
    this.eventReport = eventReport;
  }
}
