import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { ConductoresService } from './../../_service/conductores.service';
import { Conductores } from './../../_model/Conductores';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { EliminarconductorComponent } from './eliminarconductor/eliminarconductor.component';

@Component({
  selector: 'app-conductores',
  templateUrl: './conductores.component.html',
  styleUrls: ['./conductores.component.css']
})
export class ConductoresComponent implements OnInit {
  displayedColumns: string[] = ['documento', 'nombre', 'apellido', 'nick', 'direccion', 'celular', 'correo', 'acciones'];
  dataSource = new MatTableDataSource<Conductores>();
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  cantidad: number;
  pageIndex: number = 0;
  pageSize: number = 5;

  constructor(private conductorService: ConductoresService, public route: ActivatedRoute, private snackBar: MatSnackBar,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.conductorService.mensajeCambio.subscribe(data => {
      this.openSnackBar(data);
      this.listarPaginado();
    });
    this.listarPaginado();
  }

  cambiarPagina(e: any) {
    this.pageIndex = e.pageIndex;
    this.pageSize = e.pageSize;
    this.listarPaginado();
  }

  listarPaginado() {
    this.conductorService.listarPaginado(this.pageIndex, this.pageSize).subscribe(data => {
      this.dataSource = new MatTableDataSource(data.content);
      this.dataSource.sort = this.sort;
      this.cantidad = data.totalElements;
    });
  }

  abrirDialogo(conductor: Conductores) {
    const dialogRef = this.dialog.open(EliminarconductorComponent, {
      width: '400px',
      data: { conductor }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        if (result.event === 'Elimino') {
          this.openSnackBar(result.data);
          this.listarPaginado();
        } else if (result.event === 'Cancelo') {
          this.openSnackBar(result.data);
        }
      }
    });
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Información', {
      duration: 3000,
    });
  }

}
