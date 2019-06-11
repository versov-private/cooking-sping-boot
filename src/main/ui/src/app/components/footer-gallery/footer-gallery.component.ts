import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer-gallery',
  templateUrl: './footer-gallery.component.html',
  styleUrls: ['./footer-gallery.component.sass']
})
export class FooterGalleryComponent implements OnInit {

  instaGallery: string[] = [
    '/assets/images/insta-1.jpg',
    '/assets/images/insta-2.jpg',
    '/assets/images/insta-3.jpg',
    '/assets/images/insta-4.jpg',
    '/assets/images/insta-5.jpg',
    '/assets/images/insta-6.jpg'
  ];

  constructor() { }

  ngOnInit() {
  }

}
