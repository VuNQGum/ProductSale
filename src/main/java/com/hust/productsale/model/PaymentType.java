package com.hust.productsale.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "payment_type")
public class PaymentType {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "code", length = 45)
    private String code;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 255)
    @Column(name = "descript")
    private String descript;

}