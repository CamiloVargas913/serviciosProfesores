import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Vehiculo } from '../_model/vehiculo';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class VehiculoService {
  private url: string = `${environment.HOST}/vehiculos`;
  mensajeCambio = new Subject<string>();
  constructor(private http: HttpClient) { }

  listarPaginado(page: number, size: number) {
    return this.http.get<any>(`${this.url}/pageable?page=${page}&size=${size}`);
  }

  listarPorId(idVehiculo: number) {
    return this.http.get<Vehiculo>(`${this.url}/listar/${idVehiculo}`);
  }

  guardar(vehiculo: Vehiculo) {
    return this.http.post(`${this.url}/guardar`, vehiculo);
  }

  editar(vehiculo: Vehiculo) {
    return this.http.put(`${this.url}/editar`, vehiculo);
  }
  asociarVehiculo(idConductor: number, idVehiculo: number) {
    return this.http.post(`${this.url}/asociarcondcutor/${idConductor}/${idVehiculo}`, null);
  }
  desAsociarVehiculo(idConductor: number, idVehiculo: number) {
    return this.http.post(`${this.url}/desasociarconductor/${idConductor}/${idVehiculo}`, null);
  }
}
