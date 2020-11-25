import { AutoresService } from './../../../_service/autores.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Autor } from './../../../_model/Autor';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-eliminarautor',
  templateUrl: './eliminarautor.component.html',
  styleUrls: ['./eliminarautor.component.css']
})
export class EliminarautorComponent implements OnInit {

  autor: Autor;
  tipo: boolean;
  constructor(public dialogRef: MatDialogRef<EliminarautorComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private autorservice: AutoresService) { }

  ngOnInit(): void {
    this.autor = this.data.autor;
    this.tipo = this.data.tipo;
  }

  eliminar() {
    this.autorservice.eliminar(this.autor.id).subscribe(() => {
      this.dialogRef.close({ event: 'Elimino', data: 'Usuario Eliminado correctamente' });
    });

  }

  cambiaraEstado(){
    this.autorservice.cambiarEstado(this.autor.id).subscribe(() => {
      this.dialogRef.close({ event: 'Elimino', data: 'Estado usuario modificado correctamente' });
    });
  }

  cerrarDialogo() {
    this.dialogRef.close({ event: 'Cancelo', data: 'Se cancelo ' });
  }

}
