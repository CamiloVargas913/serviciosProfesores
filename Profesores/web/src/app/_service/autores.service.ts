import { environment } from './../../environments/environment';
import { Autor } from './../_model/Autor';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AutoresService {
  private url: string = `${environment.HOST}/autores`;
  mensajeCambio = new Subject<string>();
  constructor(private http: HttpClient) { }

  listarPaginado(page: number, size: number) {
    return this.http.get<any>(`${this.url}/listar/false/page=${page}/size=${size}`);
  }

  listarPorId(id: number) {
    return this.http.get<any>(`${this.url}/retornarPorIdV/${id}`);
  }

  editar(autor: Autor) {
    console.log(autor);
    return this.http.put(`${this.url}/editar`, autor);
  }

  guardar(autor: Autor) {
    return this.http.post(`${this.url}/guardar`, autor);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/eliminar/${id}`);
  }

  cambiarEstado(id: number) {
    return this.http.put(`${this.url}/cambiarEstado/${id}`, '');
  }
}
