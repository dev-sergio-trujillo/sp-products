package com.spsolutions.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "category")
@Relation(value = "product")
@EqualsAndHashCode(callSuper = false)
public class CategoryModel extends RepresentationModel<CategoryModel> {

    private Long idCategory;
    private String category;
    private Date createdAt;
    private List<ProductModel> products;
}
