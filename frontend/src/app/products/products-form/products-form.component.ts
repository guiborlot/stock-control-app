import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatDialogRef } from "@angular/material/dialog";
import { ActivatedRoute } from "@angular/router";
import { Product } from "../product";
import { ProductsService } from "../products.service";

@Component({
    selector: "app-products-form",
    templateUrl: "./products-form.component.html",
    styleUrls: ["./products-form.component.scss"],
})
export class ProductsFormComponent implements OnInit {
    form!: FormGroup;
    submitted = false;

    categories$!: any;

    constructor(
        private service: ProductsService,
        private fb: FormBuilder,
        private route: ActivatedRoute,
        private dialogRef: MatDialogRef<ProductsFormComponent>
    ) {}

    ngOnInit(): void {

        this.form = this.fb.group({
            id: [null],
            name: [
                null,[
                    Validators.required,
                    Validators.minLength(2),
                    Validators.maxLength(20)
                ]
            ],
            price: [
                null,[
                    Validators.required,
                    Validators.minLength(1)
                ]
            ],
            description: [
                null,[
                    Validators.maxLength(250)
                ]
            ],
            categories:[
                null
            ]
            // categories: [
            //     this.product.categories,[
            //         Validators.required
            //     ]
            // ]
        });
    }

    onSubmit() {
        console.log(this.form.value)
        this.service.create(this.form.value).subscribe({
            next: () => console.log,
            error: () => console.log
        })
        this.dialogRef.close();
        this.form.reset()
    }

    onCancel() {
        this.dialogRef.close();
        this.form.reset()
    }
}
