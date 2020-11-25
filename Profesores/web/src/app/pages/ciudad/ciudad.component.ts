import { CiudadService } from './../../_service/ciudad.service';
import { Departamento } from './../../_model/departamento';
import { DepartamentoService } from './../../_service/departamento.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Ciudad } from 'src/app/_model/Ciudad';

@Component({
  selector: 'app-ciudad',
  templateUrl: './ciudad.component.html',
  styleUrls: ['./ciudad.component.css']
})
export class CiudadComponent implements OnInit {
  departamento: Departamento[];
  displayedColumns: string[] = ['idCiudad', 'nombre'];
  dataSource = new MatTableDataSource<Ciudad>();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private departamentoService: DepartamentoService, private ciudadService: CiudadService) { }

  ngOnInit(): void {
    this.departamentoService.listar().subscribe(data => {
      this.departamento = data;
    });
  }
  onChange(idDepa: number): void {
    this.ciudadService.listar(idDepa).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
