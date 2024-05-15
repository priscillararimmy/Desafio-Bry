import { Component } from '@angular/core';
import { DefaultLayoutComponent } from '../../components/default-layout/default-layout.component';
import { InputComponent } from '../../components/input/input.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RegisterService } from '../../services/register.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

interface RegisterForm {
  name: FormControl,
  cpf: FormControl
}

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    DefaultLayoutComponent,
    ReactiveFormsModule,
    InputComponent
  ],
  providers: [
    RegisterService
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  registerForm!: FormGroup<RegisterForm>;

  constructor(
    private router: Router,
    private registerService: RegisterService,
    private toastService: ToastrService
  ){
    this.registerForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      cpf: new FormControl('', [Validators.required, Validators.minLength(3)])
    })
  }

  submit() {
    console.log(this.registerForm.value)
    this.registerService.register(this.registerForm.value.name, this.registerForm.value.cpf).subscribe({
      next: () => {
        this.toastService.success("Cadastro feito com sucesso!");
        this.navigate(); // Chama a função navigate após o sucesso do login
      },
      error: () => this.toastService.error("Erro inesperado!"),
    })
  }

  navigate() {
    this.router.navigate(["list"])
  }

}
