package dev.danvega.h2_demo.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String roleName;
}














