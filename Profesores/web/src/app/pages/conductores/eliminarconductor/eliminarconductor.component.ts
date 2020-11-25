import { ConductoresService } from 'src/app/_service/conductores.service';
import { Conductores } from './../../../_model/Conductores';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
@Component({
  selector: 'app-eliminarconductor',
  templateUrl: './eliminarconductor.component.html',
  styleUrls: ['./eliminarconductor.component.css']
})
export class EliminarconductorComponent implements OnInit {

  conductor: Conductores;
  constructor(public dialogRef: MatDialogRef<EliminarconductorComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private conductorservice: ConductoresService) { }

  ngOnInit(): void {
    this.conductor = this.data.conductor;
  }

  eliminar() {
    this.conductorservice.eliminar(this.conductor.idUsuario).subscribe(() => {
      this.dialogRef.close({ event: 'Elimino', data: 'Usuario Eliminado correctamente' });
    });

  }

  cerrarDialogo() {
    this.dialogRef.close({ event: 'Cancelo', data: 'Se cancelo la eliminación' });
  }

}
