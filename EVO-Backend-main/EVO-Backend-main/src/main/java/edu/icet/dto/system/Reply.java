package edu.icet.dto.system;

import edu.icet.dto.customer.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply {
    @NotBlank
    @Size(min = 1, max = 10)
    private Long replyId;

    @NotBlank
    private Review review;

    @NotBlank
    private User user;

    @NotBlank
    private String text;

}
