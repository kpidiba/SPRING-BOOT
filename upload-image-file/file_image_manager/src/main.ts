import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';


import { importProvidersFrom } from '@angular/core';
import { AppComponent } from './app/app.component';
import { BrowserModule, bootstrapApplication } from '@angular/platform-browser';
import { Routes, provideRouter } from '@angular/router';
import { PageNotFoundComponent } from './shared/page-not-found/page-not-found.component';
import { provideAnimations } from '@angular/platform-browser/animations';


const routes: Routes = [
  {
    title: 'UPLOAD LOGIN',
    path: '',
    pathMatch: "full",
    loadComponent: () => import('./features/dashboard/dashboard.component').then(m => m.DashboardComponent)
  },
  {
    path: 'dashboard',
    loadChildren: () => import('./features/uploads/route'),
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
]

bootstrapApplication(AppComponent, { providers: [provideRouter(routes), provideAnimations(), provideAnimations()] }).catch((err) => console.error(err));
