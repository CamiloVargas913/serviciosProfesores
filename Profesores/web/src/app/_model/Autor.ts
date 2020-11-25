import { Libro } from './Libro';
import { Direccion } from './Direccion';

export class Autor {
  id: number;
  nombre: string;
  apellido: string;
  fecha: string;
  estado: boolean;
  libro: Libro;
  direccion: Direccion;
}
