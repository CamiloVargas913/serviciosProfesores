import { Departamento } from './../_model/departamento';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DepartamentoService {

  url = `${environment.HOST}/departamentos`;
  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<Departamento[]>(`${this.url}/listar`);
  }
}
