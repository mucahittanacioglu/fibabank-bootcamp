package com.fiba.inventoryservice.data.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
    private String categoryName;

    @OneToMany(mappedBy="category",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Product> productList;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
