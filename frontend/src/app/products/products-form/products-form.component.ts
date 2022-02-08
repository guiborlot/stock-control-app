import { Component, Inject, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
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

    constructor(
        private service: ProductsService,
        private fb: FormBuilder,
        private route: ActivatedRoute,
        private dialogRef: MatDialogRef<ProductsFormComponent>,
        @Inject(MAT_DIALOG_DATA) public product: any
    ) {}

    ngOnInit(): void {

        this.form = this.fb.group({
            id: [this.product.dataKey.id],
            name: [
                this.product.dataKey.name,[
                    Validators.required,
                    Validators.minLength(2),
                    Validators.maxLength(20)
                ]
            ],
            category: [
                this.product.dataKey.category,[
                    Validators.required,
                    Validators.minLength(2)
                ]
            ],
            price: [
                this.product.dataKey.price,[
                    Validators.required,
                    Validators.maxLength(10)
                ]
            ],
            quantity:[
                this.product.dataKey.quantity,[
                    Validators.required,
                    Validators.maxLength(5),
                    Validators.pattern('^\\d+$')
                ]
            ]
            // categories: [
            //     this.product.categories,[
            //         Validators.required
            //     ]
            // ]
        });
    }

    onSubmit() {
        if(this.form.valid){
            this.service.save(this.form.value).subscribe({
                next: (v) => this.dialogRef.close(v),
                error: () => console.log
            })
            
            this.form.reset();
        }
    }

    onCancel() {
        this.dialogRef.close();
        this.form.reset()
    }
}
