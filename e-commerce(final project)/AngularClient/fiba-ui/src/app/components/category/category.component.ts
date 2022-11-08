import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/Category';
import { CategoryWithData } from 'src/app/models/CategoryWithData';
import { InventoryService } from 'src/app/services/inventory.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categories:Category[]=[];
  categoriesWithData:CategoryWithData[]=[];
  currentCategoryWithData!:CategoryWithData;
  constructor(private inventoryService:InventoryService,private router: Router) { }
  isSingleview:boolean = true;
  
  ngOnInit(): void {
    this.inventoryService.getCategories().subscribe(result =>{
      this.categories = result.data;
      this.categories.forEach(category => this.inventoryService.getCategoryWithProducts(category.categoryId).subscribe(result=>{
        this.categoriesWithData.push(result.data)
      }))
    })
 
  }
  getProductsWithCategory(categoryId:number){
    this.inventoryService.getCategoryWithProducts(categoryId).subscribe(result =>{
      this.currentCategoryWithData = result.data;
    })
  }
  categoryView(categorywithData:CategoryWithData) {
    
    this.isSingleview = !this.isSingleview;
    this.currentCategoryWithData = categorywithData;
  }
  categoryViewReset(){
    this.isSingleview = !this.isSingleview;
  }
}
