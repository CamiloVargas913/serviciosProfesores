import { Direccion } from './../../../_model/Direccion';
import { Autor } from './../../../_model/Autor';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { AutoresService } from './../../../_service/autores.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-agregarautor',
  templateUrl: './agregarautor.component.html',
  styleUrls: ['./agregarautor.component.css']
})
export class AgregarautorComponent implements OnInit {
  form: FormGroup;
  private id: number;
  private edicion: boolean;

  constructor(private autorService: AutoresService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.id = params.id;
      this.edicion = params.id != null;
    });
    this.formularioVacio();
    if (this.edicion === true) {
      this.cargarDatos();
    }
  }

  formularioVacio() {
    this.form = new FormGroup({
      nombre: new FormControl('', [Validators.required]),
      apellido: new FormControl('', [Validators.required]),
      direccion: new FormControl('', [Validators.required]),
      barrio: new FormControl('', [Validators.required]),
    });
  }
  cargarDatos() {
    this.autorService.listarPorId(this.id).subscribe(data => {
      this.form.get('nombre').setValue(data.nombre);
      this.form.get('apellido').setValue(data.apellido);
      this.form.get('direccion').setValue(data.direccion);
      this.form.get('barrio').setValue(data.barrio);
    });
  }

  guardar() {
    let autor = new Autor();
    let direccion = new Direccion();

    direccion.barrio = this.form.value.barrio;
    direccion.direccion = this.form.value.direccion;

    autor.nombre = this.form.value.nombre;
    autor.apellido = this.form.value.apellido;
    autor.direccion = direccion;

    if (this.edicion === true) {
      autor.id = this.id;
      this.autorService.editar(autor).subscribe((data) => {
        this.form.reset();
        this.autorService.mensajeCambio.next('Conductor editado satisfactoriamente');
        this.router.navigate(['/inicio/autor']);
      });
    } else {
      this.autorService.guardar(autor).subscribe(() => {
        this.form.reset();
        this.autorService.mensajeCambio.next('Conductor agregado satisfactoriamente');
        this.router.navigate(['/inicio/autor']);
      });
    }
  }

  get nombre() {
    return this.form.get('nombre');
  }

  get apellido() {
    return this.form.get('apellido');
  }

  get direccion() {
    return this.form.get('direccion');
  }

  get barrio() {
    return this.form.get('barrio');
  }

}
