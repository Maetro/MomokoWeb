import { Component, OnInit, Output, EventEmitter, Inject, PLATFORM_ID } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/primeng';
import { isPlatformBrowser } from '@angular/common';


@Component({
  selector: 'app-join-us-editor-form',
  templateUrl: './join-us-editor-form.component.html',
  styleUrls: ['./join-us-editor-form.component.scss']
})
export class JoinUsEditorFormComponent implements OnInit {

  @Output() return = new EventEmitter();

  authorRequestForm: FormGroup;

  submitted = false;

  constructor( private formBuilder: FormBuilder,@Inject(PLATFORM_ID) private platformId: Object, 
   private messageService: MessageService) { }

  ngOnInit() {
    this.getAuthorRequestForm();
    this.authorRequestForm.patchValue({
    });
  }

  onSubmit() {
    this.submitted = true;
    console.log(this.authorRequestForm.getRawValue());
    // stop here if form is invalid
    if (this.authorRequestForm.invalid) {
      let detail = '';
      if (this.authorF.name.errors && this.authorF.name.errors.required){
        detail += 'El nombre es obligatorio';
      } else if (this.authorF.name.errors){
        if ( this.authorF.email.errors.required){
          detail += 'El email es obligatorio';
        } else if (this.authorF.email.errors.email)
        detail += 'El email no tiene un formato correcto';
      } else if (this.authorF.acceptedPrivacy.errors){
        detail += 'Es obligatorio aceptar la política de privacidad';
      }
      if (isPlatformBrowser(this.platformId)) {
      this.messageService.add({
        severity: "error",
        summary: "Hay errores en el formulario",
        detail: detail
      });
    }
      return;
    }
    this.authorRequestForm.controls;
    const updateAuthorRequest = this.authorRequestForm.getRawValue();
  }

  volver(){
    console.log('volver');
    this.return.emit();
  }

  get authorF() {
    return this.authorRequestForm.controls;
  }

  private getAuthorRequestForm() {
    this.authorRequestForm = this.formBuilder.group({
      name: ["", Validators.required],
      email:  ["", [Validators.required, Validators.email]],
      description: [""],
      acceptedPrivacy: ["", Validators.required],
    });
  }

}
