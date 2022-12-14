package com.fiba.shoppingservice.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class CartProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartProductId;
    private long productId;
    private int salesQuantity;
    private double salesPrice;
    private double lineAmount;

    public CartProduct( long productId, int salesQuantity, double salesPrice, double lineAmount) {
        this.productId = productId;
        this.salesQuantity = salesQuantity;
        this.salesPrice = salesPrice;
        this.lineAmount = lineAmount;
    }
    public void updateLineAmount(){
        setLineAmount(getSalesPrice()*getSalesQuantity());
    }


}
