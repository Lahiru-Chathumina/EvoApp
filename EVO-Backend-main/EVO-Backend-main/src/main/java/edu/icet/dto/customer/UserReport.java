package edu.icet.dto.customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReport {
    private Long reportId;

    // UserReport is accessible through user.
}
