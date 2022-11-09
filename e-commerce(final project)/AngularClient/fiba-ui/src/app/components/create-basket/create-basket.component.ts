import { Component, OnInit } from '@angular/core';
import { ShoppingService } from 'src/app/services/shopping.service';
import { ToasterService } from 'src/app/services/toaster.service';

@Component({
  selector: 'app-create-basket',
  templateUrl: './create-basket.component.html',
  styleUrls: ['./create-basket.component.css']
})
export class CreateBasketComponent implements OnInit {
  customerName:string="Mucahit Tanacioglu"
  constructor(private shoppingService:ShoppingService,private toatrService:ToasterService) { }

  ngOnInit(): void {
  }
  createBasket(){
    console.log("Sending req")
    this.shoppingService.createCartWithName(this.customerName).subscribe(result=>{
      if(result.success){
        localStorage.setItem("cartId",result.data+"") 
        this.toatrService.successToaster(result.message)
      }
      else{
        this.toatrService.warningToaster(result.message)
      }
      
    })
  }
}
