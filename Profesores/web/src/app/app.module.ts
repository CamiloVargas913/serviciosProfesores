import { environment } from './../environments/environment';
import { ServerErrorInterceptorService } from './_shared/server-error-interceptor.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaterialModule } from './_material/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DepartamentoComponent } from './pages/departamento/departamento.component';
import { CiudadComponent } from './pages/ciudad/ciudad.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { VehiculoComponent } from './pages/vehiculo/vehiculo.component';
import { AgregarvehiculoComponent } from './pages/vehiculo/agregarvehiculo/agregarvehiculo.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ConductoresComponent } from './pages/conductores/conductores.component';
import { AgregarconductorComponent } from './pages/conductores/agregarconductor/agregarconductor.component';
import { EliminarconductorComponent } from './pages/conductores/eliminarconductor/eliminarconductor.component';
import { AsociacionComponent } from './pages/vehiculo/asociacion/asociacion.component';
import { Not404Component } from './pages/not404/not404.component';
import { ErrorComponent } from './pages/error/error.component';
import { Not401Component } from './pages/not401/not401.component';
import { HomeComponent } from './pages/home/home.component';
import { MenuComponent } from './pages/menu/menu.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { AutorComponent } from './pages/autor/autor.component';
import { AgregarautorComponent } from './pages/autor/agregarautor/agregarautor.component';
import { EliminarautorComponent } from './pages/autor/eliminarautor/eliminarautor.component';
import { LectorComponent } from './pages/lector/lector.component';
import { AgregarLectorComponent } from './pages/lector/agregar-lector/agregar-lector.component';

@NgModule({
  declarations: [
    AppComponent,
    DepartamentoComponent,
    CiudadComponent,
    VehiculoComponent,
    AgregarvehiculoComponent,
    ConductoresComponent,
    AgregarconductorComponent,
    EliminarconductorComponent,
    AsociacionComponent,
    Not404Component,
    ErrorComponent,
    Not401Component,
    HomeComponent,
    MenuComponent,
    AutorComponent,
    AgregarautorComponent,
    EliminarautorComponent,
    LectorComponent,
    AgregarLectorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    /*{
      provide: HTTP_INTERCEPTORS,
      useClass: ServerErrorInterceptorService,
      multi: true
    },*/
    { provide: LocationStrategy, useClass: HashLocationStrategy }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
