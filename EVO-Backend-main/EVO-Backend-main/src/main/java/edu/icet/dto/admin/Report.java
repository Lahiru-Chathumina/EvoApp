package edu.icet.dto.admin;

import edu.icet.dto.customer.UserReport;
import edu.icet.dto.event.EventReport;
import edu.icet.util.ReportType;
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
public class Report {
    @NotNull
    private Long reportId;

    @Size(min = 5,max = 30)
    private String title;

    private String description;

    @PastOrPresent
    private LocalDate dateCreate;

    @PastOrPresent
    private LocalDate dateModify;

    private ReportType reportType;

    private AuditReport auditReport;
    private UserReport userReport;
    private EventReport eventReport;
}
