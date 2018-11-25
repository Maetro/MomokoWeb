import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from "@angular/router";
import { CustomBlock } from "app/contenido/comunes/common-dtos/custom-block";
import { Observable } from "rxjs";
import { CustomBlockService } from "../service/custom-block.service";
import { NewCustomBlockInfo } from "../dtos/new-custom-block-info";

@Injectable({
    providedIn: "root"
})
export class NewCustomBlockResolverService implements Resolve<NewCustomBlockInfo> {
    constructor(
        private customBlockService: CustomBlockService,
        private router: Router
    ) { }

    resolve(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<NewCustomBlockInfo> {
        return this.customBlockService.getNewCustomBlockInfoForForm();
    }
}