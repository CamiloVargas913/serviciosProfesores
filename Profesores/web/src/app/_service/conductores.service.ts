import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Conductores } from '../_model/Conductores';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConductoresService {
  private url: string = `${environment.HOST}/usuarios`;
  constructor(private http: HttpClient) { }
  mensajeCambio = new Subject<string>();

  listarPaginado(page: number, size: number) {
    return this.http.get<any>(`${this.url}/pageablePorRol/4/${page}/${size}`);
  }

  listarPorId(idConductor: number) {
    return this.http.get<Conductores>(`${this.url}/listar/${idConductor}`);
  }

  guardar(conductor: Conductores) {
    return this.http.post(`${this.url}/guardar`, conductor);
  }

  editar(conductor: Conductores) {
    return this.http.put(`${this.url}/editar`, conductor);
  }
  eliminar(id: number) {
    return this.http.delete(`${this.url}/eliminar/${id}`);
  }
  listarCoductoresNoAsociados(idvehiculo: number) {
    return this.http.get<Conductores[]>(`${this.url}/listarConductorNoVehiculo/${idvehiculo}`);
  }

  listarConductoresAsociados(idvehiculo: number){
    return this.http.get<any>(`${this.url}/listarConductorVehiculo/${idvehiculo}`);
  }
}
