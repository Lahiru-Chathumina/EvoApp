package edu.icet.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BeautySaloon {
    private Long id;

    private String specialty;

    private Supplier supplier;
}