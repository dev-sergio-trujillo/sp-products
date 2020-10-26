package com.spsolutions.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "product")
@Relation(collectionRelation = "products")
@EqualsAndHashCode(callSuper = false)
public class ProductModel extends RepresentationModel<ProductModel> {

    private Long idProduct;
    private String sku;
    private Double price;
    private String name;
    private Date createdAt;
    private CategoryModel category;
}
