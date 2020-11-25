import { MatSnackBar } from '@angular/material/snack-bar';
import { ConductoresService } from 'src/app/_service/conductores.service';
import { Conductores } from './../../../_model/Conductores';
import { Vehiculo } from './../../../_model/Vehiculo';
import { VehiculoService } from 'src/app/_service/vehiculo.service';
import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
@Component({
  selector: 'app-asociacion',
  templateUrl: './asociacion.component.html',
  styleUrls: ['./asociacion.component.css']
})
export class AsociacionComponent implements OnInit {

  vehiculo: Vehiculo;
  conductores: Conductores[];
  idConductor: number;

  displayedColumns: string[] = ['nombre', 'apellido', 'acciones'];
  dataSource = new MatTableDataSource<Conductores>();
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(public dialogRef: MatDialogRef<AsociacionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private vehiculosservice: VehiculoService,
    private conductorservice: ConductoresService,private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.vehiculo = this.data.vehiculo;

    this.cargarConductoresNoAsociados();
    this.listarConductotesAsociados();
  }

  cargarConductoresNoAsociados() {
    this.conductorservice.listarCoductoresNoAsociados(this.vehiculo.idVehiculo).subscribe(data => {
      this.conductores = data;
    });
  }

  listarConductotesAsociados() {
    this.conductorservice.listarConductoresAsociados(this.vehiculo.idVehiculo).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });
  }
  agregar() {
    this.vehiculosservice.asociarVehiculo(this.idConductor, this.vehiculo.idVehiculo).subscribe(() => {
      this.cargarConductoresNoAsociados();
      this.listarConductotesAsociados();
      this.openSnackBar('Conductor asociado');
    });
  }
  desAgregar(idConductor: number) {
    this.vehiculosservice.desAsociarVehiculo(idConductor, this.vehiculo.idVehiculo).subscribe(() => {
      this.cargarConductoresNoAsociados();
      this.listarConductotesAsociados();
      this.openSnackBar('Conductor desasociado');
    });
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Información', {
      duration: 3000,
    });
  }

}
