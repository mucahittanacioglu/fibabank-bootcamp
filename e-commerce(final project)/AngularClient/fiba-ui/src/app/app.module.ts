import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BasketComponent } from './components/basket/basket.component';
import { ProductComponent } from './components/product/product.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CategoryComponent } from './components/category/category.component';
import { CategoryViewComponent } from './components/category-view/category-view.component';
import { CreateBasketComponent } from './components/create-basket/create-basket.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
const appRoutes: Routes = [
  {path:'create-basket',component:CreateBasketComponent},
  {path:'',component:CreateBasketComponent},
  {path:'category',component:CategoryComponent},
  {path:'tobasket',component:BasketComponent}
]
@NgModule({
  declarations: [
    AppComponent,
    BasketComponent,
    ProductComponent,
    NavbarComponent,
    CategoryComponent,
    CategoryViewComponent,
    CreateBasketComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    RouterModule.forRoot(appRoutes, {enableTracing: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
