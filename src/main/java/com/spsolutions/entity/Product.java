package com.spsolutions.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCT")
    private Long idProduct;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORY")
    @JsonManagedReference
    private Category category;
}
