import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepgComponent} from './pages/homepg/homepg.component';
import { SignInComponent} from './pages/sign-in/sign-in.component';
import { SignUpComponent} from './pages/sign-up/sign-up.component';
import { ProductComponent} from './pages/product/product.component';
import { ProductDetailComponent} from './pages/product-detail/product-detail.component';
import { StockComponent } from './pages/stock/stock.component'
import { SigninGuard} from './guard/signin.guard';


const routes: Routes = [
  { path: 'sign-in', component: SignInComponent},
  { path: 'sign-up', component: SignUpComponent},
  { path: 'home', component: HomepgComponent},
  { path: 'products', component: ProductComponent, canActivate: [SigninGuard]},
  { path: 'products/:id', component: ProductDetailComponent, canActivate: [SigninGuard]},
  { path: 'stock/:id', component: StockComponent}
];

@NgModule({
  // Registering the RouterModule.forRoot() in the AppModule imports makes the Router service available everywhere in the application.
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

  //定义两层路由，使用嵌套的方式解决模版的占屏幕问题。
})
export class AppRoutingModule { }
