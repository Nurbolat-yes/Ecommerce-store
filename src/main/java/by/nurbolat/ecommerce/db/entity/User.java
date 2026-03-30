package by.nurbolat.ecommerce.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 30,nullable = false,unique = true)
    private String email;

    @Column(length = 90,nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoles roles;
}
