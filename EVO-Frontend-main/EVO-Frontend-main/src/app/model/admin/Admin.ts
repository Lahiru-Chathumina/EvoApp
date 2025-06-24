import { AdminType } from "../../../utils/AdminType";
import { AuditHistory } from "./AuditHistory";

export class Admin {
  adminId: number;
  auditHistory: AuditHistory;
  type: AdminType;

  constructor(adminId: number, auditHistory: AuditHistory, type: AdminType) {
    this.adminId = adminId;
    this.auditHistory = auditHistory;
    this.type = type;
  }
}
