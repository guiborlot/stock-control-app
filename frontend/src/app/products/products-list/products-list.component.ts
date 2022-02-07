import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { Product } from "../product";
import { MatDialog } from "@angular/material/dialog";
import { ProductsService } from "../products.service";
import { ProductsFormComponent } from "../products-form/products-form.component";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    selector: "app-products-list",
    templateUrl: "./products-list.component.html",
    styleUrls: ["./products-list.component.scss"],
})
export class ProductsListComponent implements OnInit {
    dataSource$!: Observable<any>;

    displayedColumns: string[] = [
        "id",
        "name",
        "category",
        "price",
        "quantity",
        "actions",
    ];

    constructor(private service: ProductsService, public dialog: MatDialog, private router: Router, private route: ActivatedRoute) {}

    ngOnInit(): void {
        this.dataSource$ = this.service.list();
    }

    addProduct() {
        const product = {}
        const dialogRef = this.dialog.open(ProductsFormComponent, {
            // maxHeight: '95vh',
            minWidth: "500px",
            // width: '25vw',
            data: {
                dataKey: product
            }
        });

        dialogRef.afterClosed().subscribe((result) => {
            console.log("The dialog was closed");
            window.location.reload();
        });
    }

    onEdit(product: any) {
        //this.router.navigate(["edit", id], {relativeTo: this.route});
        const dialogRef = this.dialog.open(ProductsFormComponent, {
            minWidth: "500px",
            data:{
                dataKey: product
            }
        });

        dialogRef.afterClosed().subscribe((result) => {
            console.log("The dialog was closed");
            window.location.reload();
        });

    }
    onDelete(id: number) {
        this.service.delete(id).subscribe({
            complete: () => window.location.reload()
        })
    }
}
