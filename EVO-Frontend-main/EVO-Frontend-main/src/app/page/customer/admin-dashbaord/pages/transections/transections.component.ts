import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Transections } from '../../../../../model/Transections';

@Component({
  selector: 'app-transections',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './transections.component.html',
  styleUrl: './transections.component.css',
})
export class TransectionsComponent {
  filter: Transections = {
    date: '',
    transectionId: '',
    amount: null,
    supplierName: '',
    supplierType: '',
    customerName: '',
  };

  transection: Transections[] = [
    {
      date: new Date('2025-03-25').toDateString(),
      transectionId: 'T1',
      amount: 35000,
      supplierName: 'Supplier 1',
      supplierType: 'Type 1',
      customerName: 'Nimal',
    },
    {
      date: new Date('2025-03-25').toDateString(),
      transectionId: 'T1',
      amount: 35000,
      supplierName: 'Supplier 1',
      supplierType: 'Type 1',
      customerName: 'Nimal',
    },
    {
      date: new Date('2025-03-25').toDateString(),
      transectionId: 'T1',
      amount: 35000,
      supplierName: 'Supplier 1',
      supplierType: 'Type 1',
      customerName: 'Nimal',
    },
    {
      date: new Date('2025-03-25').toDateString(),
      transectionId: 'T1',
      amount: 35000,
      supplierName: 'Supplier 1',
      supplierType: 'Type 1',
      customerName: 'Nimal',
    },
    {
      date: new Date('2025-03-25').toDateString(),
      transectionId: 'T1',
      amount: 35000,
      supplierName: 'Supplier 1',
      supplierType: 'Type 1',
      customerName: 'Nimal',
    },
    {
      date: new Date('2025-03-25').toDateString(),
      transectionId: 'T1',
      amount: 35000,
      supplierName: 'Supplier 1',
      supplierType: 'Type 1',
      customerName: 'Nimal',
    },
  ];

  get filteredTransection(): Transections[] {
    return this.transection.filter((transection) => {
      const matchesDate = this.filter.date
        ? transection.date.includes(new Date(this.filter.date).toDateString()) 
        : true;
  
      const matchesTransactionId = this.filter.transectionId
        ? transection.transectionId.toLowerCase().includes(this.filter.transectionId.toLowerCase())
        : true;
  
      const matchesAmount =
        this.filter.amount !== null
          ? transection.amount === this.filter.amount
          : true;
  
      const matchesSupplierName = this.filter.supplierName
        ? transection.supplierName
            .toLowerCase()
            .includes(this.filter.supplierName.toLowerCase())
        : true;
  
      const matchesSupplierType = this.filter.supplierType
        ? transection.supplierType
            .toLowerCase()
            .includes(this.filter.supplierType.toLowerCase())
        : true;
  
      const matchesCustomerName = this.filter.customerName
        ? transection.customerName
            .toLowerCase()
            .includes(this.filter.customerName.toLowerCase())
        : true;
  
      return (
        matchesDate &&
        matchesTransactionId &&
        matchesAmount &&
        matchesSupplierName &&
        matchesSupplierType &&
        matchesCustomerName
      );
    });
  }

  clearFilters(): void {
    this.filter = {
      date: '',
      transectionId: '',
      amount: null,
      supplierName: '',
      supplierType: '',
      customerName: ''
    };
  }
}
