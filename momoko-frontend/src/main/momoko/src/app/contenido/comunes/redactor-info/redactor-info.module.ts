import { RedactorInfoComponent } from "./redactor-info.component";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { SocialDataIconsModule } from "app/contenido/comun/social-data-icons/social-data-icons.module";

@NgModule({
    imports: [CommonModule, RouterModule, SocialDataIconsModule],
    declarations: [
        RedactorInfoComponent
    ],
    exports: [
        RedactorInfoComponent
    ]
  })
  export class RedactorInfoModule {}