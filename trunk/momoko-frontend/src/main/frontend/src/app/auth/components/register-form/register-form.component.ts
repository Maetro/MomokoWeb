import {Component, OnInit} from '@angular/core';

import {Message} from 'primeng/components/common/api';

import {AuthService} from '../../services/auth.service';
import {User} from '../../../dtos/user';

@Component({
    selector: 'app-register-form',
    templateUrl: './register-form.component.html'
})
export class RegisterFormComponent implements OnInit {

    model: User;
    messages: Message[] = [];

    constructor(private authService: AuthService) {
    }

    ngOnInit(): void {
        this.model = new User();
    }

    onSubmit(): void {
        this.messages = [];
        this.authService
            .register(this.model)
            .subscribe(isRegistered => {
                if (isRegistered) {
                    this.messages.push({severity: 'info', summary: 'Registrado correctamente!'});
                } else {
                    this.messages.push({severity: 'error', summary: 'Email en uso'});
                }
            });
    }
}
