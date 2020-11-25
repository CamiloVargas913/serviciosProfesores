import { MatSnackBar } from '@angular/material/snack-bar';
import { LectorAutor } from './../../../_model/LectorAutor';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Lector } from './../../../_model/lector';
import { Autor } from './../../../_model/Autor';
import { AutoresService } from './../../../_service/autores.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-asociarautorlector',
  templateUrl: './asociarautorlector.component.html',
  styleUrls: ['./asociarautorlector.component.css']
})
export class AsociarautorlectorComponent implements OnInit {

  form: FormGroup;
  listaAutor: Array<Autor>;
  lector: Lector;
  constructor(public dialogRef: MatDialogRef<AsociarautorlectorComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private autorservice: AutoresService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.lector = this.data.lector;
    this.crearForm();
    this.cargarAutores();
  }

  crearForm() {
    this.form = new FormGroup({
      autor: new FormControl('', [Validators.required]),
      info: new FormControl('', [Validators.required]),
    });
  }
  cargarAutores() {
    this.autorservice.listarPaginado(0, 100).subscribe(data => {
      this.listaAutor = data.content;
    });
  }

  guardar() {
    let lectorAutor = new LectorAutor();
    let autor = new Autor();
    let lector = new Lector();

    autor.id = this.form.get('autor').value;
    lector.id = this.lector.id;
    lectorAutor.autor = autor;
    lectorAutor.lector = lector;
    lectorAutor.infoAdicional = this.form.get('info').value;

    this.autorservice.asociar(lectorAutor).subscribe(data => {
      this.openSnackBar('Asociado Correctamente');
      this.dialogRef.close();
    });
    console.log(lectorAutor);
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Información', {
      duration: 3000,
    });
  }

  cerrarDialogo() {
    this.dialogRef.close({ event: 'Cancelo', data: 'Se cancelo ' });
  }

  get autor() {
    return this.form.get('autor');
  }
  get info() {
    return this.form.get('info');
  }
}
