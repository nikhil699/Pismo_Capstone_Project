package Pismo.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;

@Entity
@Table(name = "Accounts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_id")
    private Long accountId;

    @Column(name = "Document_Number", nullable = false, unique = true)
    private String documentNumber;
}