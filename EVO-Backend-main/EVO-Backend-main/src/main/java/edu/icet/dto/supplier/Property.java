package edu.icet.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private Long propertyId;

    private String name;

    private Long hallId;

    private Long roomId;

    private Long poolId;

    private Long outdoorAreaId;

    private List<String> imageUrl;
}