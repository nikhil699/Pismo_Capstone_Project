package Pismo.demo.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "operation_types")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_type_id")
    private Long operationTypeId;

    @Column(name = "description", nullable = false, unique = true)
    private String description;
}
