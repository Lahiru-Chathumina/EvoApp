
package edu.icet.dto.system;

import edu.icet.dto.supplier.Supplier;
import edu.icet.util.SupplierCategoryType;
import jakarta.validation.constraints.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuickReplies {

    @NotNull
    @Positive
    private Long replyID;

    @NotNull
    private Supplier supplier;

    @NotNull
    @Size(min = 5 , max = 225)
    private String content;

    @NotNull
    private SupplierCategoryType category;


}
