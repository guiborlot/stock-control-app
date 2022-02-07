import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ProductResolverGuard } from "./guards/product-resolver.guard";
import { ProductsFormComponent } from "./products-form/products-form.component";
import { ProductsListComponent } from "./products-list/products-list.component";

const routes: Routes = [
    { path: "", component: ProductsListComponent },
    { path: "new", component: ProductsFormComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class ProductsRoutingModule {}
