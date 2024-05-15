import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterResponse } from '../types/register-response.type';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  apiUrl: string ="http://localhost:8080/users"

  constructor(private httpClient: HttpClient) {}

  register(name: string, cpf: string) {
    return this.httpClient.post<RegisterResponse>(this.apiUrl, { name, cpf }).pipe(
      tap((value) => {
        sessionStorage.setItem("userName", value.name)
        sessionStorage.setItem("userCpf", value.cpf)
      })
    );
  }
}
