package edu.icet.dto.system;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchHistory {
    @NotNull
    private Long searchId;
    @PastOrPresent
    private LocalDate date;
    @PastOrPresent
    private String time;
    @Size(min = 3,max = 30)
    private String description;
    private String title;
    private Long customerId;

}
