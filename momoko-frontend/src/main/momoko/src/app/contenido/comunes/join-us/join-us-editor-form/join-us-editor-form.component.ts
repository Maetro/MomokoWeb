import { isPlatformBrowser } from "@angular/common";
import { Component, EventEmitter, Inject, OnInit, Output, PLATFORM_ID } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { JoinUsService } from "../join-us.service";

declare var $: any;
@Component({
  selector: "app-join-us-editor-form",
  templateUrl: "./join-us-editor-form.component.html",
  styleUrls: ["./join-us-editor-form.component.scss"]
})
export class JoinUsEditorFormComponent implements OnInit {
  @Output()
  return: EventEmitter<String> = new EventEmitter<String>();

  authorRequestForm: FormGroup;

  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    @Inject(PLATFORM_ID) private platformId: Object,
    private joinUsService: JoinUsService
  ) {}

  ngOnInit() {
    this.getAuthorRequestForm();
    this.authorRequestForm.patchValue({});
  }

  onSubmit() {
    this.submitted = true;
    console.log(this.authorRequestForm.getRawValue());
    // stop here if form is invalid
    if (this.authorRequestForm.invalid) {
      let detail = "";
      if (this.authorF.name.errors && this.authorF.name.errors.required) {
        detail += "El nombre es obligatorio";
      } else if (this.authorF.name.errors) {
        if (this.authorF.email.errors.required) {
          detail += "El email es obligatorio";
        } else if (this.authorF.email.errors.email)
          detail += "El email no tiene un formato correcto";
      } else if (this.authorF.acceptedPrivacy.errors) {
        detail += "Es obligatorio aceptar la política de privacidad";
      }
      if (isPlatformBrowser(this.platformId)) {
        $.growl.error({ message: "Hay errores en el formulario: " + detail });
      }
      return;
    }
    this.authorRequestForm.controls;
    const updateAuthorRequest = this.authorRequestForm.getRawValue();
    this.joinUsService.sendEmail(updateAuthorRequest).subscribe(response => {
      if (isPlatformBrowser(this.platformId)) {
        $.growl.notice({
          message:
            "Se ha enviado el mensaje correctamente, te responderemos lo antes posible."
        });
      }
      this.return.emit("SEND");
    });
  }

  volver() {
    console.log("volver");
    this.return.emit("RETURN");
  }

  get authorF() {
    return this.authorRequestForm.controls;
  }

  private getAuthorRequestForm() {
    this.authorRequestForm = this.formBuilder.group({
      name: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
      description: [""],
      acceptedPrivacy: ["", Validators.required]
    });
  }
}
