import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../models/Cart';
import { CartProduct } from '../models/CartProduct';
import { DataResult } from '../models/DataResult';
import { Result } from '../models/Result';
@Injectable({
  providedIn: 'root'
})
export class ShoppingService {
  apiUrl = "http://localhost:8080/commerce/shopping/";
  constructor(private httpClient: HttpClient) { }

  getCartById(cartId:number):Observable<DataResult<Cart>>{
    return this.httpClient.get<DataResult<Cart>>(this.apiUrl+"cart/find/"+cartId);
 
  }
  cartCheckout(cartId:number):Observable<Result>{
    return this.httpClient.get<Result>(this.apiUrl+"cart/checkout/"+cartId);
  }
  removeProductFromCart(cartId:number,productId:number):Observable<Result>{
    return this.httpClient.delete<Result>(this.apiUrl+"shopping/cart/"+cartId+"/remove/"+productId);
  }
  addProducttoCart(product:CartProduct):Observable<Result>{
    return this.httpClient.post<Result>(this.apiUrl+"cart/add/",product);
  }
  createDefaultCart():Observable<DataResult<number>>{
    return this.httpClient.get<DataResult<number>>(this.apiUrl+"cart/create");
  }
  createCartWithName(customerName:string):Observable<DataResult<number>>{
    return this.httpClient.get<DataResult<number>>(this.apiUrl+"cart/create/"+customerName);
  }

}
