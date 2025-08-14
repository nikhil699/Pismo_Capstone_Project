package Pismo.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "OperationsTypes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OperationType_ID")
    private Long operationTypeId;

    @Column(name = "Description",nullable = false, unique = true)
    private String description;
}