import { ActivatedRoute, Params, Router } from '@angular/router';
import { Rol } from './../../../_model/Rol';
import { Tipodocumento } from '../../../_model/Tipodocumento';
import { Ciudad } from 'src/app/_model/Ciudad';
import { Departamento } from './../../../_model/departamento';
import { CiudadService } from './../../../_service/ciudad.service';
import { DepartamentoService } from './../../../_service/departamento.service';
import { Conductores } from './../../../_model/Conductores';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ConductoresService } from 'src/app/_service/conductores.service';

@Component({
  selector: 'app-agregarconductor',
  templateUrl: './agregarconductor.component.html',
  styleUrls: ['./agregarconductor.component.css']
})
export class AgregarconductorComponent implements OnInit {

  form: FormGroup;
  departamento: Departamento[];
  ciudadlist: Ciudad[];
  private id: number;
  private edicion: boolean;

  constructor(private conductorService: ConductoresService, private departamentoService: DepartamentoService,
    private ciudadService: CiudadService,
    private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.id = params.id;
      this.edicion = params.id != null;
    });
    this.cargarDepartamentos();
    this.formularioVacio();
    if (this.edicion === true) {
      this.cargarDatos();
    }
  }
  cargarDepartamentos() {
    this.departamentoService.listar().subscribe(data => {
      this.departamento = data;
    });
  }

  formularioVacio() {
    this.form = new FormGroup({
      documento: new FormControl('', [Validators.required]),
      nombre: new FormControl('', [Validators.required]),
      apellido: new FormControl('', [Validators.required]),
      nick: new FormControl('', [Validators.required]),
      clave: new FormControl('', [Validators.required]),
      direccion: new FormControl('', [Validators.required]),
      celular: new FormControl('', [Validators.required]),
      celularAux: new FormControl('', [Validators.required]),
      correo: new FormControl('', [Validators.required, Validators.email]),
      ciudad: new FormControl('', [Validators.required]),
      departamento: new FormControl('', [Validators.required])
    });
  }
  cargarDatos() {
    this.conductorService.listarPorId(this.id).subscribe(data => {
      this.form.get('documento').setValue(data.documento);
      this.form.get('nombre').setValue(data.nombre);
      this.form.get('apellido').setValue(data.apellido);
      this.form.get('nick').setValue(data.nick);
      this.form.get('direccion').setValue(data.direccion);
      this.form.get('celular').setValue(data.celular);
      this.form.get('celularAux').setValue(data.celularAux);
      this.form.get('correo').setValue(data.correo);
      this.form.get('departamento').setValue(data.ciudad.departamento.idDepartamento);
      this.onChange(data.ciudad.departamento.idDepartamento);
      this.form.get('ciudad').setValue(data.ciudad.idCiudad);
    });
  }

  guardar() {
    let conductor = new Conductores();
    let tipodocu = new Tipodocumento();
    let rol = new Rol();
    let ciudad = new Ciudad();
    tipodocu.idTipoDocumento = 1;
    rol.idRol = 4;
    ciudad.idCiudad = this.form.value['ciudad'];
    conductor.documento = this.form.value['documento'];
    conductor.nombre = this.form.value['nombre'];
    conductor.apellido = this.form.value['apellido'];
    conductor.nick = this.form.value['nick'];
    conductor.clave = this.form.value['clave'];
    conductor.direccion = this.form.value['direccion'];
    conductor.celular = this.form.value['celular'];
    conductor.celularAux = this.form.value['celularAux'];
    conductor.correo = this.form.value['correo'];
    conductor.tipoDocumento = tipodocu;
    conductor.rol = rol;
    conductor.ciudad = ciudad;

    if (this.edicion === true) {
      conductor.idUsuario = this.id;
      this.conductorService.editar(conductor).subscribe((data) => {
        this.form.reset();
        this.conductorService.mensajeCambio.next('Conductor editado satisfactoriamente');
        this.router.navigate(['/inicio/conductor']);
      });
    } else {
      this.conductorService.guardar(conductor).subscribe(() => {
        this.form.reset();
        this.conductorService.mensajeCambio.next('Conductor agregado satisfactoriamente');
        this.router.navigate(['/inicio/conductor']);
      });
    }
  }

  onChange(idDepa: number): void {
    this.ciudadService.listar(idDepa).subscribe(data => {
      this.ciudadlist = data;
    });
  }

  get documento() {
    return this.form.get('documento');
  }

  get nombre() {
    return this.form.get('nombre');
  }

  get apellido() {
    return this.form.get('apellido');
  }

  get nick() {
    return this.form.get('nick');
  }

  get clave() {
    return this.form.get('clave');
  }

  get direccion() {
    return this.form.get('direccion');
  }

  get celular() {
    return this.form.get('celular');
  }

  get celularAux() {
    return this.form.get('celularAux');
  }
  get correo() {
    return this.form.get('correo');
  }

  get tipodocumento() {
    return this.form.get('tipodocumento');
  }

  get rol() {
    return this.form.get('rol');
  }

  get ciudad() {
    return this.form.get('ciudad');
  }

}
