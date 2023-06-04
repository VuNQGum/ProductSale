package com.hust.productsale.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "code", nullable = false, length = 45)
    private String code;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 250)
    @Column(name = "short_descript", length = 250)
    private String shortDescript;

    @Lob
    @Column(name = "descript")
    private String descript;

    @NotNull
    @Column(name = "standard_cost", nullable = false, precision = 19, scale = 4)
    private BigDecimal standardCost;

    @Column(name = "is_new")
    private Byte isNew;

    @Column(name = "is_special")
    private Byte isSpecial;

    @Column(name = "is_deleted")
    private Byte isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}