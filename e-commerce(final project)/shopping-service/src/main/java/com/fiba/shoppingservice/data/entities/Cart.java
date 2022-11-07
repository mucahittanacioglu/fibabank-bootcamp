package com.fiba.shoppingservice.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;
    private String customerName;
    private double totalAmount;
    private int status;
    @OneToMany(mappedBy="cart",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<CartProduct> cartProducts;

    public Cart(String customerName) {
        this.customerName = customerName;
    }

    public void addCartProduct(CartProduct cartProduct) {
        cartProducts.add(cartProduct);
        cartProduct.setCart(this);
        updateTotalAmount();
    }

    public void removeCartProduct(CartProduct cartProduct) {
        cartProducts.remove(cartProduct);
        cartProduct.setCart(null);
        updateTotalAmount();
    }
    private void updateTotalAmount(){
        double total = cartProducts.stream()
                .mapToDouble(product->product.getLineAmount()).sum();
        setTotalAmount(total);
    }
}
