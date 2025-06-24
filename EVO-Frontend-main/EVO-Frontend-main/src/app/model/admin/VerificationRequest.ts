export class VerificationRequest {
  requestID: number;
  responseDate: Date;
  submissionDate: Date;
  status: string;
  verificationDocument: string;
  name: string;
  email: string;
  phoneNumber: string;

  constructor(
    requestID: number,
    responseDate: Date,
    submissionDate: Date,
    status: string,
    verificationDocument: string,
    name: string,
    email: string,
    phoneNumber: string
  ) {
    this.requestID = requestID;
    this.responseDate = responseDate;
    this.submissionDate = submissionDate;
    this.status = status;
    this.verificationDocument = verificationDocument;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }
}
