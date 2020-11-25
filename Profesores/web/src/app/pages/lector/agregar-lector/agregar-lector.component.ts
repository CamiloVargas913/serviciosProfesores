import { LectorService } from './../../../_service/lector.service';
import { Lector } from './../../../_model/lector';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-agregar-lector',
  templateUrl: './agregar-lector.component.html',
  styleUrls: ['./agregar-lector.component.css']
})
export class AgregarLectorComponent implements OnInit {


  form: FormGroup;
  validationMessages = {
    nombre: [
      { type: 'required', message: '* Campo requerido' }
    ],
    apellido: [
      { type: 'required', message: '* Campo requerido' }
    ]
  };
  private id: number;
  edicion: boolean;
  constructor(private route: ActivatedRoute,
    private router: Router,
    private lectorService: LectorService) {
      this.form = this.crearForm();
     }

  ngOnInit(): void {
    this.validarUrl();
    if (this.edicion === true) {
      this.cargarDatosEditar();
    }
  }

  crearForm() {
    return new FormGroup({
      nombre: new FormControl('', [Validators.required]),
      apellido: new FormControl('', [Validators.required])
    });
  }

  guardar() {
    let lector = new Lector();
    lector.apellido = this.form.get('apellido').value;
    lector.nombre = this.form.get('nombre').value;
    if (this.edicion === true) {
      lector.id = this.id;
      this.lectorService.editar(lector).subscribe(() => {
        this.lectorService.mensajeCambio.next('Lector modificado correctamente');
        this.router.navigate(['/inicio/lector']);
      });
    } else {
      this.lectorService.agregar(lector).subscribe(() => {
        this.form.reset();
        this.lectorService.mensajeCambio.next('Libro agregado correctamente');
        this.router.navigate(['/inicio/lector']);
      });
    }
  }

  cargarDatosEditar() {
    this.lectorService.listarId(this.id).subscribe(data => {
      this.form.get('nombre').setValue(data.nombre);
      this.form.get('apellido').setValue(data.apellido);
    });
  }

  validarUrl() {
    this.route.params.subscribe((params: Params) => {
      this.id = params['id'];
      this.edicion = params['id'] != null
    })
  }

}
