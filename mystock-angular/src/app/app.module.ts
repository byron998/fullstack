import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatTabsModule } from '@angular/material/tabs';
import { MatListModule } from '@angular/material/list';

// import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
/* bootstrap 按需导入，可以使我们最终的打包文件更小 */
import { NgbAlertModule} from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepgComponent} from './pages/homepg/homepg.component';
import { ProductComponent } from './pages/product/product.component';
import { ProductDetailComponent } from './pages/product-detail/product-detail.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { FooterComponent } from './component/footer/footer.component';
import { ChartsComponent } from './component/charts/charts.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { SignUpComponent } from './pages/sign-up/sign-up.component';
import { LeftListComponent } from './component/left-list/left-list.component'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MoneyPipe } from './pipe/money.pipe';
import { JwtInterceptor } from './interceptor/jwt.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxEchartsModule } from 'ngx-echarts';

@NgModule({
  declarations: [
    AppComponent,
    HomepgComponent,
    ProductComponent,
    ProductDetailComponent,
    NavbarComponent,
    FooterComponent,
    SignInComponent,
    SignUpComponent,
    ChartsComponent,
    LeftListComponent,
    MoneyPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbAlertModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatButtonModule, 
    MatCheckboxModule,
    MatTabsModule,
    MatListModule,
    NgxEchartsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],

})
export class AppModule { }
