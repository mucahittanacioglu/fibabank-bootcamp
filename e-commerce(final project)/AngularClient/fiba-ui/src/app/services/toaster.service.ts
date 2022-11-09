import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ToasterService {

  constructor(private toaster:ToastrService) { }

  successToaster(message:string){
    this.toaster.success(message);
  }
  infoToaster(message:string){
    this.toaster.info(message);
  }
  errorToaster(message:string){
    this.toaster.error(message);
  }
  warningToaster(message:string){
    this.toaster.warning(message);
  }
}
