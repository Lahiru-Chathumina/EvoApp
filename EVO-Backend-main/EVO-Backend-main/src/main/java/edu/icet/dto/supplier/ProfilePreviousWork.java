package edu.icet.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePreviousWork {
    private Long previousWorkId;

    private String title;

    private String description;

    private LocalDateTime complectionDate;

    private Long customerId;

    private Long supplierId;

    private List<String> imageUrl;
}