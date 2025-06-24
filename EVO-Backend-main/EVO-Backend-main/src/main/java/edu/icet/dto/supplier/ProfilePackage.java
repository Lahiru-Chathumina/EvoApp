package edu.icet.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePackage {
    private Long packageId;

    private String description;

    private String packageName;

    private Double price;

    private String status;

    private Long supplierId;

    private List<String> imageUrl;

}