import { CartProduct } from "./CartProduct";

export interface Cart{
    cartId:number;
    customerName:string;
    cartProducts:CartProduct[];
}