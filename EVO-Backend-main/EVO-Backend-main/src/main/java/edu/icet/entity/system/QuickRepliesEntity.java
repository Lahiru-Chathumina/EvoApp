
package edu.icet.entity.system;

import edu.icet.entity.supplier.SupplierEntity;
import edu.icet.util.SupplierCategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "QuickReplies")
public class QuickRepliesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyID;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SupplierCategoryType category;


}
