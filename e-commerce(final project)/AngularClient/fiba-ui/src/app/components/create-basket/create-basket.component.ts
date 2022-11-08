import { Component, OnInit } from '@angular/core';
import { ShoppingService } from 'src/app/services/shopping.service';

@Component({
  selector: 'app-create-basket',
  templateUrl: './create-basket.component.html',
  styleUrls: ['./create-basket.component.css']
})
export class CreateBasketComponent implements OnInit {
  customerName:string="Mucahit Tanacioglu"
  constructor(private shoppingService:ShoppingService) { }

  ngOnInit(): void {
  }
  createBasket(){
    console.log("Sending req")
    this.shoppingService.createCartWithName(this.customerName).subscribe(result=>{
      localStorage.setItem("cartId",result.data+"") 
      console.log(result)
    })
  }
}
