import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from "@angular/router";
import { CustomBlock } from "app/contenido/comunes/common-dtos/custom-block";
import { Observable } from "rxjs";
import { CustomBlockService } from "../service/custom-block.service";

@Injectable({
    providedIn: "root"
})
export class EditCustomBlockResolverService implements Resolve<CustomBlock[]> {
    constructor(
        private customBlockService: CustomBlockService,
        private router: Router
    ) { }

    resolve(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<CustomBlock[]> {
        return this.customBlockService.getCustomBlocks();
    }
}