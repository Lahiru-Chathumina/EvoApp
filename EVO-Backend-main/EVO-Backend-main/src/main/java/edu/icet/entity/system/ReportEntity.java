package edu.icet.entity.system;

import edu.icet.util.ReportType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "reports")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @NotNull
    @Column(nullable = false)
    private String title;

    private String description;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate dateCreate;

    @PastOrPresent
    private LocalDate dateModify;

    private String category;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportType reportType;
}
