package com.hust.productsale.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "ship_date")
    private Date shipDate;

    @Size(max = 45)
    @Column(name = "city", length = 45)
    private String city;

    @Size(max = 45)
    @Column(name = "province", length = 45)
    private String province;

    @Size(max = 45)
    @Column(name = "ward", length = 45)
    private String ward;

    @Size(max = 45)
    @Column(name = "address", length = 45)
    private String address;

    @Column(name = "shipping_fee", precision = 19, scale = 4)
    private BigDecimal shippingFee;

    @Size(max = 10)
    @NotNull
    @Column(name = "order_status", nullable = false, length = 10)
    private String orderStatus;

    @Column(name = "paid_date")
    private Date paidDate;

    @NotNull
    @Column(name = "payment_type", nullable = false)
    private Integer paymentType;

    @Column(name = "customer_paid", precision = 19, scale = 4)
    private BigDecimal customerPaid;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

}