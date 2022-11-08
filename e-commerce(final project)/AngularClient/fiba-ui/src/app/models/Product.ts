
export class Product {
    productId:number;
    productName:string;
    salesPrice: number;
    categoryName:string;
    constructor(productId:number,productName:string,salesPrice: number,categoryName:string){
      this.productId = productId;
      this.productName = productName;
      this.salesPrice =salesPrice;
      this.categoryName = categoryName; 
    }
}