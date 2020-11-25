import { LectorAutor } from './../../../_model/LectorAutor';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AutoresService } from './../../../_service/autores.service';
import { AsociarautorlectorComponent } from './../../lector/asociarautorlector/asociarautorlector.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Lector } from './../../../_model/lector';
import { Autor } from './../../../_model/Autor';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-desasociar-lector',
  templateUrl: './desasociar-lector.component.html',
  styleUrls: ['./desasociar-lector.component.css']
})
export class DesasociarLectorComponent implements OnInit {

  autorSeleccionado: Autor;
  displayedColumns: string[] = ['nombre', 'apellido', 'acciones'];
  lectores = new MatTableDataSource<LectorAutor>();
  pageIndex = 0;
  pageSize = 5;
  constructor(public dialogRef: MatDialogRef<AsociarautorlectorComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private autorservice: AutoresService, private snackBar: MatSnackBar,) { }

  ngOnInit(): void {
    this.autorSeleccionado = this.data.autor;
    this.cargarlectores();
  }

  cargarlectores() {
    this.autorservice.lectoreAsiciados(this.autorSeleccionado.id).subscribe(data => {
      this.lectores = data;
      console.log(data);
    });
  }

  desacociar(idLector: number) {
    this.autorservice.desasociarLector(this.autorSeleccionado.id, idLector).subscribe(data => {
      this.openSnackBar('Desasociado Correctamente');
      this.cargarlectores();
    });
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Información', {
      duration: 3000,
    });
  }

  cerrarDialogo() {
    this.dialogRef.close({ event: 'Cancelo', data: 'Se cancelo ' });
  }

}
