import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { take, tap } from "rxjs";
import { Product } from "./product";

@Injectable({
    providedIn: "root",
})
export class ProductsService {
    private readonly API = "api/products";

    constructor(private http: HttpClient) {}

    list(){
        return this.http.get(this.API).pipe(tap(console.log));
    }

    loadByID(id: number) {
        return this.http.get<Product>(`${this.API}/${id}`).pipe(take(1));
    }

    create(product: any){
        return this.http.post(`${this.API}`, product).pipe(take(1))
    }
}
