import { Ciudad } from 'src/app/_model/Ciudad';
import { Rol } from './Rol';
import { Tipodocumento } from './Tipodocumento';
export class Conductores {
  idUsuario: number;
  documento: string;
  nombre: string;
  apellido: string;
  nick: string;
  clave: string;
  direccion: string;
  celular: string;
  celularAux: string;
  correo: string;
  tipoDocumento: Tipodocumento;
  rol: Rol;
  ciudad: Ciudad;
}
