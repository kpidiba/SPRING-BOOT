import { Route } from "@angular/router";

export default [
  {
    path: 'upload',
    loadComponent: () => import('./upload/upload.component').then(m => m.UploadComponent)
  },
  {
    path: 'upload-crop',
    loadComponent: () => import('./upload-crop/upload-crop.component').then(m => m.UploadCropComponent)
  },
] as Route[];