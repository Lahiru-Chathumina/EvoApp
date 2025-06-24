import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reports',
  imports: [ReactiveFormsModule],
  templateUrl: './reports.component.html',
  styleUrl: './reports.component.css',
})
export class ReportsComponent {
  userReportForm: FormGroup;
  eventReportForm: FormGroup;
  supplierReportForm: FormGroup;
  systemReportForm: FormGroup;
  auditReportForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.userReportForm = this.fb.group({
      userId: ['', [Validators.required]],
      reportType: ['summary', Validators.required],
    });

    this.eventReportForm = this.fb.group({
      eventId: ['', [Validators.required]],
      reportType: ['summary', Validators.required],
    });

    this.supplierReportForm = this.fb.group({
      supplierId: ['', [Validators.required]],
      reportType: ['summary', Validators.required],
    });

    this.systemReportForm = this.fb.group({
      systemId: ['', [Validators.required]],
      reportType: ['summary', Validators.required],
    });

    this.auditReportForm = this.fb.group({
      auditId: ['', [Validators.required]],
      reportType: ['summary', Validators.required],
    });
  }

  submitUserReport() {
    if (this.userReportForm.valid) {
      console.log('User Report Data:', this.userReportForm.value);
      Swal.fire({
        icon: 'success',
        title: ' Added Successful',
        text: '',
      });
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Fail',
        text: 'Required fill all fields and Correct Input Detatils',
      });
    }
  }

  submitEventReport() {
    if (this.eventReportForm.valid) {
      console.log('Event Report Data:', this.eventReportForm.value);
      Swal.fire({
        icon: 'success',
        title: ' Added Successful',
        text: '',
      });
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Fail',
        text: 'Required fill all fields and Correct Input Detatils',
      });
    }
  }

  submitSupplierReport() {
    if (this.supplierReportForm.valid) {
      console.log('Supplier Report Data:', this.supplierReportForm.value);
      Swal.fire({
        icon: 'success',
        title: ' Added Successful',
        text: '',
      });
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Fail',
        text: 'Required fill all fields and Correct Input Detatils',
      });
    }
  }

  submitSystemReport() {
    if (this.systemReportForm.valid) {
      console.log('System Report Data:', this.systemReportForm.value);
      Swal.fire({
        icon: 'success',
        title: ' Added Successful',
        text: '',
      });
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Fail',
        text: 'Required fill all fields and Correct Input Detatils',
      });
    }
  }

  submitAuditReport() {
    if (this.auditReportForm.valid) {
      console.log('Audit Report Data:', this.auditReportForm.value);
      Swal.fire({
        icon: 'success',
        title: ' Added Successful',
        text: '',
      });
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Fail',
        text: 'Required fill all fields and Correct Input Detatils',
      });
    }
  }
}
