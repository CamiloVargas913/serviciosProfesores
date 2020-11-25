import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Lector } from '../_model/lector';

@Injectable({
  providedIn: 'root'
})
export class LectorService {

  private url = `${environment.HOST}/autores`;
  mensajeCambio = new Subject<string>();
  constructor(private http: HttpClient) { }

  listarPaginado(page: number, size: number) {
    return this.http.get<any>(`${this.url}/listarLector/page=${page}/size=${size}`);
  }

  listarId(idLector: number){
    return this.http.get<any>(`${this.url}/retornarPorId/${idLector}`);
  }

  agregar(lector: Lector){
    return this.http.post(`${this.url}/guardarLector`, lector);
  }

  editar(lector: Lector){
    return this.http.put(`${this.url}/editarLEctor`, lector);
  }
}
