import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { Product } from "../product";
import { MatDialog } from '@angular/material/dialog';
import { ProductsService } from "../products.service";
import { ProductsFormComponent } from "../products-form/products-form.component";

@Component({
    selector: "app-products-list",
    templateUrl: "./products-list.component.html",
    styleUrls: ["./products-list.component.scss"],
})
export class ProductsListComponent implements OnInit {
    dataSource$!: Observable<any>;

    displayedColumns: string[] = ["id", "name", "category", "price", "quantity"];

    constructor(private service: ProductsService, public dialog: MatDialog) {}

    ngOnInit(): void {
        this.dataSource$ = this.service.list()
    }

    addProduct(){
        const dialogRef = this.dialog.open(ProductsFormComponent, {
            // maxHeight: '95vh',
            minWidth: '400px',
            // width: '25vw',
          });
      
          dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
            // window.location.reload();
          });
    }
}
