import { Result } from "./Result";

export class DataResult<T> extends Result{


    data:T;
    constructor(success:boolean,message:string,data:T){
        super(success,message);
        this.data=data;
    }
}