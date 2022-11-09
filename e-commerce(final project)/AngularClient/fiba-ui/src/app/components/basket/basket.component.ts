import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/models/Cart';
import { CartProduct } from 'src/app/models/CartProduct';
import { ShoppingService } from 'src/app/services/shopping.service';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {
  basket:Cart ={
  cartId:0,
  customerName:"Test",
  cartProducts:[]
  } 
  totalSum:number=0;
  constructor(private shoppingService:ShoppingService) { }

  ngOnInit(): void {
    this.shoppingService.getCartById(Number(localStorage.getItem("cartId"))).subscribe(result=>{
      result.success ? this.basket=result.data:console.log(result)
      this.updateTotalSum();
    })
    
  }
  checkout(){
    this.shoppingService.cartCheckout(this.basket.cartId).subscribe(result => {
      console.log(result)
    });
  }
  updateTotalSum(){
    this.totalSum = this.basket.cartProducts.reduce((total,currentProduct)=>total+currentProduct.lineAmount,0)
  }
  incrementByOne(basketItem:CartProduct){
    basketItem.salesQuantity+=1;
    this.shoppingService.addProducttoCart(basketItem).subscribe(result=>{
      result.success ? "":console.log(result.message)
    });
    this.updateLineAmount(basketItem);
    this.updateTotalSum();
  }
  
  decrementByOne(basketItem:CartProduct){
    if(basketItem.salesQuantity==1){
      this.basket.cartProducts = this.basket.cartProducts.filter(product=>product.productId != basketItem.productId)
      this.shoppingService.removeProductFromCart(this.basket.cartId,basketItem.cartProductId).subscribe(result=>{
        if(result.success) {
          this.updateTotalSum();
        }
        console.log(result.message)
      });
      return;
    }
    basketItem.salesQuantity -=1;
    this.shoppingService.removeProductFromCart(this.basket.cartId,basketItem.cartProductId).subscribe(result=>{
      if(result.success){
        this.updateLineAmount(basketItem);
        this.updateTotalSum();
      } 
      console.log(result.message)
      
    });
    
  }
  updateLineAmount(basketItem:CartProduct){
    basketItem.lineAmount = basketItem.salesPrice*basketItem.salesQuantity;
    this.updateTotalSum();
  }
}
