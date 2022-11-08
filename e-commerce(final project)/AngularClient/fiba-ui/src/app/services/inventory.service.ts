import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/Product';
import { Observable } from 'rxjs';
import { DataResult } from '../models/DataResult';
import { Category } from '../models/Category';
import { CategoryWithData } from '../models/CategoryWithData';
@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  apiUrl = "http://localhost:8080/commerce/inventory"
  
  constructor(private httpClient: HttpClient) { }
  getProductById(productId:number):Observable<DataResult<Product>>{
    return this.httpClient.get<DataResult<Product>>(this.apiUrl+"/product/"+productId);
  }
  getCategories():Observable<DataResult<Category[]>>{
    return this.httpClient.get<DataResult<Category[]>>(this.apiUrl+"/categories")
  }
  getProductsByCategory(categoryId:number):Observable<DataResult<Product>>{
    return this.httpClient.get<DataResult<Product>>(this.apiUrl+"/products/"+categoryId)
  }
  getCategoryWithProducts(categoryId:number):Observable<DataResult<CategoryWithData>>{
    return this.httpClient.get<DataResult<CategoryWithData>>(this.apiUrl+"/category/"+categoryId)
  }
}
