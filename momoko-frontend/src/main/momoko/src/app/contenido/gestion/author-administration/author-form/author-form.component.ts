import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Author } from 'app/dtos/autor';
import { environment } from 'environments/environment';

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


  constructor( private formBuilder: FormBuilder,
    private router: Router,
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
        this.author = data.userEditRequest.user;});
        
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

  get f() {
    return this.authorForm.controls;
  }

}
