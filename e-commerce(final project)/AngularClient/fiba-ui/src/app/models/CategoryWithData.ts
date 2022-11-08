import { Category } from "./Category";
import { Product } from "./Product";

export class CategoryWithData implements Category{
    categoryId!: number;
    categoryName!: string;
    products: Product[] = [];
}