import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/models/Cart';
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
  constructor(private shoppingService:ShoppingService) { }

  ngOnInit(): void {
    this.shoppingService.getCartById(Number(localStorage.getItem("cartId"))).subscribe(result=>{
      result.success ? this.basket=result.data:console.log(result)
    })
  }
  createOrder(){
    
  }

}
