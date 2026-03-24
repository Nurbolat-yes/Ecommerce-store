package by.nurbolat.ecommerce.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    private String reviewOwner;

    private String comment;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;

}
