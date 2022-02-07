import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Resolve, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Product } from '../product';
import { ProductsService } from '../products.service';

@Injectable({
  providedIn: 'root'
})
export class ProductResolverGuard implements Resolve<Product> {
    
    constructor(private service: ProductsService){}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product>{
        if(route.params && route.params['id']){
            return this.service.loadByID(route.params['id']);
        }

        return of({
            id: null,
            name: null,
            price: null,
            description: null,
            categories: null
        });
    }
  
}