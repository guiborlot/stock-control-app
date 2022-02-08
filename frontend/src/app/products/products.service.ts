import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { take, tap } from "rxjs";
import { environment } from "src/environments/environment";
import { Product } from "./product";

@Injectable({
    providedIn: "root",
})
export class ProductsService {
    private readonly API = `${environment.API}products`;

    constructor(private http: HttpClient) {}

    list(){
        return this.http.get(this.API).pipe(tap(console.log));
    }

    loadByID(id: number) {
        return this.http.get<Product>(`${this.API}/${id}`).pipe(take(1));
    }

    save(product: any){
        if(product.id){
            return this.update(product);
        }
        return this.create(product);
    }

    private create(product: any){
        return this.http.post(`${this.API}`, product).pipe(take(1));
    }

    private update(product: any){
        return this.http.put(`${this.API}/${product.id}`, product).pipe(take(1));
    }

    delete(id: number){
        return this.http.delete(`${this.API}/${id}`).pipe(take(1));
    }
}
