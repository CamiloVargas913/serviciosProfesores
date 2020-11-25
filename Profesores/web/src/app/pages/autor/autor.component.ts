import { AutoresService } from './../../_service/autores.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { MatSort } from '@angular/material/sort';
import { Autor } from './../../_model/Autor';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit, ViewChild } from '@angular/core';
import { EliminarautorComponent } from './eliminarautor/eliminarautor.component';

@Component({
  selector: 'app-autor',
  templateUrl: './autor.component.html',
  styleUrls: ['./autor.component.css']
})
export class AutorComponent implements OnInit {
  displayedColumns: string[] = ['nombre', 'apellido', 'direccion', 'barrio', 'estado', 'acciones'];
  dataSource = new MatTableDataSource<Autor>();
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  cantidad: number;
  pageIndex: number = 0;
  pageSize: number = 5;

  constructor(private autoreService: AutoresService, public route: ActivatedRoute, private snackBar: MatSnackBar,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.autoreService.mensajeCambio.subscribe(data => {
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
    this.autoreService.listarPaginado(this.pageIndex, this.pageSize).subscribe(data => {
      this.dataSource = new MatTableDataSource(data.content);
      this.dataSource.sort = this.sort;
      this.cantidad = data.totalElements;
    });
  }

  abrirDialogo(autor: Autor, tipo: boolean) {
    const dialogRef = this.dialog.open(EliminarautorComponent, {
      width: '400px',
      data: { autor , tipo }
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
