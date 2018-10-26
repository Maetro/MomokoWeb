import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Author } from 'app/dtos/autor';
import { environment } from 'environments/environment';
import { MessageService } from 'primeng/primeng';
import { FileUploadService } from '../../services/file-upload.service';
import { AuthorService } from '../service/author.service';

@Component({
  selector: 'app-author-form',
  templateUrl: './author-form.component.html',
  styleUrls: ['./author-form.component.scss']
})
export class AuthorFormComponent implements OnInit {

  private log = environment.log;

  authorForm: FormGroup;
  authorId: string;
  
  author: Author;

  submitted = false;

  constructor( private formBuilder: FormBuilder,
    private router: Router,
    private messageService: MessageService,
    private authorService: AuthorService,
    private fileUploadService: FileUploadService,
    private route: ActivatedRoute) { 

  }

  ngOnInit() {
    if (this.log) {
      console.log('Builder ListaFilteresComponent');
    }

    this.authorId = this.route.snapshot.paramMap.get("id");
    this.getAuthorForm();
    if (this.authorId) {
      this.route.data.subscribe(data => {
        this.author = data.userEditRequest.user;
        this.authorForm.patchValue({
          name: this.author.name,
          authorUrl: this.author.authorUrl,
          birhtYear: this.author.birhtYear,
          deathYear: this.author.deathYear,
          birthCountry: this.author.birthCountry,
          description: this.author.description,
          avatar: this.author.avatar,
          authorHeaderImage: this.author.authorHeaderImage
        });
      });
    } else{
      this.authorForm.patchValue({
        avatar: 'https://momoko.es/images/',
        authorHeaderImage: 'https://momoko.es/images/'
      });      
    }

  }

  private getAuthorForm() {
    this.authorForm = this.formBuilder.group({
      name: ["", Validators.required],
      authorUrl:  ["", Validators.required],
      birhtYear: ["", Validators.pattern('[0-9]*')],
      deathYear: ["", Validators.pattern('[0-9]*')],
      birthCountry: [""],
      description: [""],
      avatar: [""],
      authorHeaderImage: [""]
    });
  }

  get authorF() {
    return this.authorForm.controls;
  }

  fileChangeCabecera($event): void {
    this.fileUploadService.fileChange($event, 'cabeceras-redactores').subscribe
      (urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        this.messageService.add({
          severity: "success",
          summary: "Imagen guardada correctamente",
          detail: "Via Servidor"
        });
        this.authorForm.patchValue({
          authorHeaderImage: urlImagenNueva
        });
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      });
  }

  fileChangeAvatar($event): void {
    this.fileUploadService.fileChange($event, 'avatares').subscribe
      (urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        this.messageService.add({
          severity: "success",
          summary: "Imagen guardada correctamente",
          detail: "Via Servidor"
        });
        this.authorForm.patchValue({
          avatar: urlImagenNueva
        });
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      });
  }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.authorForm.invalid) {
      return;
    }
    this.authorForm.controls;
    const updateAuthorRequest = this.authorForm.getRawValue();
    this.authorService.saveAuthor(updateAuthorRequest).subscribe(res => {
      if (res.authorUrl === updateAuthorRequest.authorUrl) {
        this.messageService.add({
          severity: "success",
          summary: "El autor se ha guardado correctamente.",
          detail: "Via MessageService"
        });
        this.router.navigate(["/gestion/autores"]);
      } else {
        alert("ERROR!! :-(");
      }
    });
  }

}
