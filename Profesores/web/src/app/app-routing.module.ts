import { AgregarautorComponent } from './pages/autor/agregarautor/agregarautor.component';
import { AutorComponent } from './pages/autor/autor.component';
import { MenuComponent } from './pages/menu/menu.component';
import { Not401Component } from './pages/not401/not401.component';
import { GuardianService } from './_shared/guardian.service';
import { HomeComponent } from './pages/home/home.component';
import { ErrorComponent } from './pages/error/error.component';
import { Not404Component } from './pages/not404/not404.component';
import { AgregarconductorComponent } from './pages/conductores/agregarconductor/agregarconductor.component';
import { ConductoresComponent } from './pages/conductores/conductores.component';
import { AgregarvehiculoComponent } from './pages/vehiculo/agregarvehiculo/agregarvehiculo.component';
import { VehiculoComponent } from './pages/vehiculo/vehiculo.component';
import { CiudadComponent } from './pages/ciudad/ciudad.component';
import { DepartamentoComponent } from './pages/departamento/departamento.component';
import { AgregarLectorComponent } from './pages/lector/agregar-lector/agregar-lector.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LectorComponent } from './pages/lector/lector.component';



const routes: Routes = [
  { path: '', component: MenuComponent },
  {
    path: 'inicio', component: MenuComponent, children: [
      { path: 'home', component: HomeComponent },
      {
        path: 'autor', component: AutorComponent, children: [
          { path: 'agregar', component: AgregarautorComponent },
          { path: 'edicion/:id', component: AgregarautorComponent }
        ]
      },
      {
        path: 'lector', component: LectorComponent, children: [
          { path: 'agregar', component: AgregarLectorComponent },
          { path: 'edicion/:id', component: AgregarLectorComponent }
        ]
      }
    ]
  },

  { path: 'error/:status/:message', component: ErrorComponent },
  { path: 'not-401', component: Not401Component },
  { path: '**', component: Not404Component }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
