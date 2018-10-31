import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Author } from 'app/dtos/autor';
import { environment } from 'environments/environment';
import { MessageService } from 'primeng/primeng';
import { FileUploadService } from '../../services/file-upload.service';
import { AuthorService } from '../service/author.service';
import { UtilService } from 'app/services/util/util.service';

@Component({
  selector: 'app-author-form',
  templateUrl: './author-form.component.html',
  styleUrls: ['./author-form.component.scss']
})
export class AuthorFormComponent implements OnInit {

  private log = environment.log;

  authorForm: FormGroup;
  authorUrl: string;
  
  author: Author;

  customURL = false;

  submitted = false;

  constructor( private formBuilder: FormBuilder,
    private router: Router,
    private messageService: MessageService,
    private authorService: AuthorService,
    private fileUploadService: FileUploadService,
    private util: UtilService,
    private route: ActivatedRoute) { 

  }

  ngOnInit() {
    if (this.log) {
      console.log('Builder ListaFilteresComponent');
    }

    this.authorUrl = this.route.snapshot.paramMap.get("url");
    this.getAuthorForm();
    if (this.authorUrl) {
      this.route.data.subscribe(data => {
        this.author = data.author;
        this.authorForm.patchValue({
          authorId: +this.author.authorId,
          name: this.author.name,
          authorUrl: this.author.authorUrl,
          birhtYear: this.author.birhtYear,
          deathYear: this.author.deathYear,
          birthCountry: this.author.birthCountry,
          description: this.author.description,
          avatar: this.author.avatar,
          twitter: this.author.twitter,
          facebook: this.author.facebook,
          instagram: this.author.instagram,
          youtube: this.author.youtube,
          webpage: this.author.webpage,
          authorHeaderImage: this.author.authorHeaderImage
        });
      });
    } 
  }

  private getAuthorForm() {
    this.authorForm = this.formBuilder.group({
      authorId: [""],
      name: ["", Validators.required],
      authorUrl:  ["", Validators.required],
      birhtYear: ["", Validators.pattern('[0-9]*')],
      deathYear: [""],
      birthCountry: [""],
      twitter: [""],
      facebook: [""],
      instagram: [""],
      youtube: [""],
      webpage:[""],
      description: [""],
      avatar: [""],
      authorHeaderImage: [""]
    });
  }

  get authorF() {
    return this.authorForm.controls;
  }

  changeName(newValue: string) {
    if (!this.customURL) {
      const newUrl = this.util.convertToSlug(newValue);
      this.authorForm.patchValue({
        authorUrl: newUrl
      });
    }
  }

  fileChangeCabecera($event): void {
    this.fileUploadService.fileChange($event, 'author-headers').subscribe
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
    this.fileUploadService.fileChange($event, 'author-avatar').subscribe
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

  volver(){
    this.router.navigate(["/gestion/autores"]);
  }

}
