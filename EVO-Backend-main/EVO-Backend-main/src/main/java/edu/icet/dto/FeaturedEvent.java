package edu.icet.dto;

import edu.icet.dto.supplier.Supplier;
import edu.icet.util.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeaturedEvent {
    private Integer title;
    private EventType type;
    private String customerName;
    private Double eventBudget;
    private String description;
    private List<Supplier> supplierList;
}
