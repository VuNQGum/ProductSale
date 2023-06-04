package com.hust.productsale.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Column(name = "value_percentage", nullable = false)
    private Integer valuePercentage;

    @NotNull
    @Column(name = "date_start", nullable = false)
    private Date dateStart;

    @NotNull
    @Column(name = "date_end", nullable = false)
    private Date dateEnd;

    @Size(max = 55)
    @Column(name = "name", length = 55)
    private String name;

    @Column(name = "is_fixed", columnDefinition = "TINYINT(1)")
    private Boolean isFixed = true;

}