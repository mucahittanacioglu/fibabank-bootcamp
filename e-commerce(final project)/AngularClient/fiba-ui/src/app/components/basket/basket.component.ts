import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/models/Cart';
import { CartProduct } from 'src/app/models/CartProduct';
import { ShoppingService } from 'src/app/services/shopping.service';
import { ToasterService } from 'src/app/services/toaster.service';

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
  constructor(private shoppingService:ShoppingService,private toatrService:ToasterService) { }

  ngOnInit(): void {
    this.shoppingService.getCartById(Number(localStorage.getItem("cartId"))).subscribe(result=>{
      result.success ? this.basket=result.data:console.log(result)
      this.updateTotalSum();
    })
    
  }
  checkout(){
    this.shoppingService.cartCheckout(this.basket.cartId).subscribe(result => {
      if(result.success){
        this.toatrService.successToaster(result.message)
      }else{
        this.toatrService.warningToaster(result.message)
      }
    });
  }
  updateTotalSum(){
    this.totalSum = this.basket.cartProducts.reduce((total,currentProduct)=>total+currentProduct.lineAmount,0)
  }
  incrementByOne(basketItem:CartProduct){
    
    this.shoppingService.addProducttoCart(basketItem).subscribe(result=>{
      if(result.success){
        basketItem.salesQuantity+=1;
        this.toatrService.successToaster(result.message)
      }else{
        this.toatrService.errorToaster(result.message)
      }
       
    });
    this.updateLineAmount(basketItem);
    this.updateTotalSum();
  }
  
  decrementByOne(basketItem:CartProduct){
    if(basketItem.salesQuantity==1){
      
      this.shoppingService.removeProductFromCart(this.basket.cartId,basketItem.cartProductId).subscribe(result=>{
        if(result.success) {
          this.basket.cartProducts = this.basket.cartProducts.filter(product=>product.productId != basketItem.productId)
          this.toatrService.successToaster(result.message)
          this.updateTotalSum();
        }else{
          this.toatrService.errorToaster(result.message)
        }        
      });
      return
    }else{
      
      this.shoppingService.removeProductFromCart(this.basket.cartId,basketItem.cartProductId).subscribe(result=>{
        if(result.success){
          this.toatrService.warningToaster(result.message)
          basketItem.salesQuantity -=1;
          this.updateLineAmount(basketItem);
          this.updateTotalSum();
        } else{
          this.toatrService.errorToaster(result.message)
        }
        
        
      });
    }
  }
  updateLineAmount(basketItem:CartProduct){
    basketItem.lineAmount = basketItem.salesPrice*basketItem.salesQuantity;
    this.updateTotalSum();
  }
}
