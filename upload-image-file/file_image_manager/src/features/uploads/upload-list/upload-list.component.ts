import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableDataSource } from '@angular/material/table';
import { MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { ImageDto } from 'src/core/interfaces/ImageDto';
import { FileuploadService } from 'src/core/services/fileupload.service';
import { Router } from '@angular/router';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { Subscription } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { MatButtonModule } from '@angular/material/button';


@Component({
  selector: 'app-upload-list',
  standalone: true,
  imports: [CommonModule, MatPaginatorModule, MatTooltipModule, MatButtonModule, MatSortModule, MatIconModule, MatInputModule, SweetAlert2Module, MatFormFieldModule, MatTableModule],
  templateUrl: './upload-list.component.html',
  styleUrls: ['./upload-list.component.css']
})
export class UploadListComponent implements OnInit, OnDestroy {
  subscription1$!: Subscription;
  subscription2$!: Subscription;
  imagePreview!: string;
  public images!: ImageDto[];
  @ViewChild(MatPaginator) paginator !: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  public dataSource!: MatTableDataSource<ImageDto>;
  displayedColumns: string[] = ['id', 'nom', 'image', 'action'];
  constructor(private service: FileuploadService, private router: Router, private toastr: ToastrService) {

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
    this.getAllImages();
  }

  getAllImages() {
    this.subscription1$ = this.service.getAllImages().subscribe({
      next: (datas) => {
        this.images = datas;
        this.dataSource = new MatTableDataSource(this.images);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }, error: () => {
        this.toastr.error("UPLOAD FAILED", "ERROR");
      }
    });
  }

  getImageSrc(file: Uint8Array): string {
    console.log( file);
    
    if (file) {
      return 'data:image/png;base64,' + this.arrayBufferToBase64(file);
    }
    return "assets/default.png";
  }

  arrayBufferToBase64(buffer: ArrayBuffer) {
    let binary = '';
    const bytes = new Uint8Array(buffer);
    const len = bytes.byteLength;
  
    for (let i = 0; i < len; i++) {
      binary += String.fromCharCode(bytes[i]);
    }
  
    return btoa(binary);
  }


  download(name: string) {
    this.subscription2$ = this.service.downloadImage(name);
  }

  ngOnDestroy(): void {
    this.subscription1$.unsubscribe();
    this.subscription2$.unsubscribe();
  }

}
