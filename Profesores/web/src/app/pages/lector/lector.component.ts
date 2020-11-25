import { Lector } from './../../_model/lector';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { LectorService } from './../../_service/lector.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-lector',
  templateUrl: './lector.component.html',
  styleUrls: ['./lector.component.css']
})
export class LectorComponent implements OnInit {


  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['nombre', 'apellido'];
  lectores = new MatTableDataSource<Lector>();
  pageIndex = 0;
  pageSize = 5;
  cantidadDataTable: number;
  constructor(public dialog: MatDialog,
    private snackBar: MatSnackBar,
    public route: ActivatedRoute,
    private lectorService: LectorService) { }

  ngOnInit(): void {
    this.lectorService.mensajeCambio.subscribe(data => {
      this.listarPaginado();
      this.openSnackBar(data);
    });
    this.listarPaginado();
  }

  cambiarPagina(e: any) {
    this.pageIndex = e.pageIndex;
    this.pageSize = e.pageSize;
    this.listarPaginado();
  }

  listarPaginado() {
    this.lectorService.listarPaginado(this.pageIndex, this.pageSize).subscribe(data => {
      this.lectores = new MatTableDataSource<Lector>(data);
      this.lectores.paginator = this.paginator;
      this.lectores.sort = this.sort;
    });
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Información', {
      duration: 2500,
    });
  }


}
