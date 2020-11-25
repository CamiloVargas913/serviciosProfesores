import { AsociacionComponent } from './asociacion/asociacion.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { VehiculoService } from './../../_service/vehiculo.service';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { Vehiculo } from './../../_model/Vehiculo';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-vehiculo',
  templateUrl: './vehiculo.component.html',
  styleUrls: ['./vehiculo.component.css']
})
export class VehiculoComponent implements OnInit {

  displayedColumns: string[] = ['placa', 'modelo', 'marca', 'tipoVehiuclo', 'capacidad', 'acciones'];
  dataSource = new MatTableDataSource<Vehiculo>();

  @ViewChild(MatSort, { static: true }) sort: MatSort;

  cantidad: number;
  pageIndex: number = 0;
  pageSize: number = 5;

  constructor(private vehiculoService: VehiculoService, public route: ActivatedRoute,
    public dialog: MatDialog, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.vehiculoService.mensajeCambio.subscribe(data => {
      this.openSnackBar(data);
      this.listarPaginado();
    })
    this.listarPaginado();
  }

  cambiarPagina(e: any) {
    this.pageIndex = e.pageIndex;
    this.pageSize = e.pageSize;
    this.listarPaginado();
  }

  listarPaginado() {
    this.vehiculoService.listarPaginado(this.pageIndex, this.pageSize).subscribe(data => {
      this.dataSource = new MatTableDataSource(data.content);
      this.dataSource.sort = this.sort;
      this.cantidad = data.totalElements;
    });
  }
  openSnackBar(message: string) {
    this.snackBar.open(message, 'Información', {
      duration: 3000,
    });
  }

  abrirDialogo(vehiculo: Vehiculo) {

    const dialogRef = this.dialog.open(AsociacionComponent, {
      width: '80%',
      data: { vehiculo }
    });
  }

}
