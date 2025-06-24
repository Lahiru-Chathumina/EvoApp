import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OLD_CustomerPaymentFilter } from '../../../model/CustomerPaymentFilter';
import { CustomerPayment } from '../../../model/CustomerPayment';


@Component({
  selector: 'app-payments-customer',
  imports: [CommonModule, FormsModule],
  templateUrl: './payments-customer.component.html',
  styleUrl: './payments-customer.component.css'
})
export class PaymentsCustomerComponent {

  filter: OLD_CustomerPaymentFilter = {
    searchCustomer: '',
    startDate: '',
    endDate: '',
    minAmount: null,
    maxAmount: null
  }

  customerPayments: CustomerPayment[] = [
    {
      date: new Date("2025-03-25").toDateString(),
      transactionId: "T1",
      supplier: "Supplier 1",
      supplierType: "Type 1",
      amount: 35000,
      remarks: "remark 1"
    },
    {
      date: new Date("2025-03-26").toDateString(),
      transactionId: "T1",
      supplier: "Supplier 1",
      supplierType: "Type 1",
      amount: 35000,
      remarks: "Remark 1"
    },
    {
      date: new Date("2025-04-25").toDateString(),
      transactionId: "T2",
      supplier: "Supplier 2",
      supplierType: "Type 2",
      amount: 45000,
      remarks: "Remark 2"
    },
    {
      date: new Date("2025-04-25").toDateString(),
      transactionId: "T3",
      supplier: "Supplier 3",
      supplierType: "Type 3",
      amount: 55000,
      remarks: "Remark 3"
    },
    {
      date: new Date("2025-04-25").toDateString(),
      transactionId: "T4",
      supplier: "Supplier 4",
      supplierType: "Type 4",
      amount: 60000,
      remarks: "Remark 4"
    },
    {
      date: new Date("2025-04-25").toDateString(),
      transactionId: "T5",
      supplier: "Supplier 5",
      supplierType: "Type 5",
      amount: 70000,
      remarks: "Remark 5"
    }
  ]

  get filteredPayments():CustomerPayment[] {
    return this.customerPayments.filter(payment => {
      // Search by supplier name
      const matchesSearchCustomer = this.filter.searchCustomer ?
        payment.supplier.toLowerCase().includes(this.filter.searchCustomer.toLowerCase()) :
        true;

      // Filter by minimum amount
      const meetsMinAmount = this.filter.minAmount !== null ?
        payment.amount >= this.filter.minAmount :
        true;

      // Filter by maximum amount
      const meetsMaxAmount = this.filter.maxAmount !== null ?
        payment.amount <= this.filter.maxAmount :
        true;

      // Filter by date range
      let meetsDateRange = true;

      if (this.filter.startDate || this.filter.endDate) {
        const paymentDate = new Date(payment.date);

        if (this.filter.startDate) {
          const startDateObj = new Date(this.filter.startDate);
          meetsDateRange = meetsDateRange && paymentDate >= startDateObj;
        }

        if (this.filter.endDate) {
          const endDateObj = new Date(this.filter.endDate);
          meetsDateRange = meetsDateRange && paymentDate <= endDateObj;
        }
      }

      // Return true if all conditions are met
      return matchesSearchCustomer && meetsMinAmount && meetsMaxAmount && meetsDateRange;
    });
  }

  // Clear filters
  clearFilters(): void {
    this.filter.searchCustomer = '';
    this.filter.minAmount = null;
    this.filter.maxAmount = null;
    this.filter.startDate = '';
    this.filter.endDate = '';
  }
}
