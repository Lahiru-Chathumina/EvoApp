import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OLD_SupplierPayment } from '../../../../model/SupplierPayment';
import { OLD_SupplierPaymentFilter } from '../../../../model/SupplierPaymentFilter';


@Component({
  selector: 'app-payments',
  imports: [CommonModule, FormsModule],
  templateUrl: './payments.component.html',
  styleUrl: './payments.component.css'
})
export class PaymentsComponent {
  filter: OLD_SupplierPaymentFilter = {
    searchSupplier: '',
    startDate: '',
    endDate: '',
    minAmount: null,
    maxAmount: null
  }

  supplierPayments: OLD_SupplierPayment[] = [
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

  get filteredPayments(): OLD_SupplierPayment[] {
    return this.supplierPayments.filter(payment => {

      const matchesSearchSupplier = this.filter.searchSupplier ?
        payment.supplier.toLowerCase().includes(this.filter.searchSupplier.toLowerCase()) :
        true;

      const meetsMinAmount = this.filter.minAmount !== null ?
        payment.amount >= this.filter.minAmount :
        true;

      const meetsMaxAmount = this.filter.maxAmount !== null ?
        payment.amount <= this.filter.maxAmount :
        true;

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

      return matchesSearchSupplier && meetsMinAmount && meetsMaxAmount && meetsDateRange;
    });
  }

  clearFilters(): void {
    this.filter.searchSupplier = '';
    this.filter.minAmount = null;
    this.filter.maxAmount = null;
    this.filter.startDate = '';
    this.filter.endDate = '';
  }
}
