import { Component, OnInit,Input } from '@angular/core';
import { CartProduct } from 'src/app/models/CartProduct';
import { Product } from 'src/app/models/Product';
import { InventoryService } from 'src/app/services/inventory.service';
import { ShoppingService } from 'src/app/services/shopping.service';
import { ToasterService } from 'src/app/services/toaster.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @Input() product:Product ={
    productId:1,
    productName:"Test",
    salesPrice: 25.2,
    categoryName:"Test2"
  };

  constructor(private inventoryService:InventoryService,private shoppingService:ShoppingService,private toatrService:ToasterService) { }

  ngOnInit(): void {

  }
  addToCart(product:Product){
    let cartId =  localStorage.getItem("cartId")
    let cartProduct = {} as CartProduct;
    cartProduct.productName=product.productName;
    cartProduct.salesQuantity = 1;
    cartProduct.salesPrice = product.salesPrice;
    cartProduct.productId = product.productId;
    if(cartId == null){
      alert("Create cart first!")  
      return
      }
      cartProduct.cartId = Number(localStorage.getItem("cartId"))
        this.shoppingService.addProducttoCart(cartProduct).subscribe(result  =>{
          if(result.success){
            this.toatrService.successToaster(result.message)
          }else{
            this.toatrService.errorToaster(result.message)
          }
        })
    }
  }


