import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ciudad } from '../_model/Ciudad';

@Injectable({
  providedIn: 'root'
})
export class CiudadService {

  url = `${environment.HOST}/departamentos/ciudad`;
  constructor(private http: HttpClient) { }

  listar(idDepa: number) {
    return this.http.get<Ciudad[]>(`${this.url}/listarPorDepartamnto/${idDepa}`);
  }
}
