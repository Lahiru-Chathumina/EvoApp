import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-blogs',
  imports: [CommonModule],
  templateUrl: './blogs.component.html',
  styleUrls: ['./blogs.component.css']  
})
export class BlogsComponent {
  
  isReadMoreVisible1: boolean = false;
  isReadMoreVisible2: boolean = false;
  isReadMoreVisible3: boolean = false;
  isReadMoreVisible4: boolean = false;
  isReadMoreVisible5: boolean = false;
  isReadMoreVisible6: boolean = false;
  isReadMoreVisible7: boolean = false;
  isReadMoreVisible8: boolean = false;
 


  
  toggleReadMore1(): void {
    this.isReadMoreVisible1 = !this.isReadMoreVisible1;
  }

  toggleReadMore2(): void {
    this.isReadMoreVisible2 = !this.isReadMoreVisible2;
  }

  toggleReadMore3(): void{
    this.isReadMoreVisible3 = !this.isReadMoreVisible3;
  }

  toggleReadMore4(): void{
    this.isReadMoreVisible4 = !this.isReadMoreVisible4;
  }

  toggleReadMore5(): void{
    this.isReadMoreVisible5 = !this.isReadMoreVisible5;
  }

  toggleReadMore6(): void{
    this.isReadMoreVisible6 = !this.isReadMoreVisible6;
  }

  toggleReadMore7(): void{
    this.isReadMoreVisible7 = !this.isReadMoreVisible7;
  }

  toggleReadMore8(): void{
    this.isReadMoreVisible8 = !this.isReadMoreVisible8;
  }


  likeCount1: number = 0;
  isLiked1: boolean = false;

  likeCount2: number = 0;
  isLiked2: boolean = false;

  likeCount3: number = 0;
  isLiked3: boolean = false;

  likeCount4: number = 0;
  isLiked4: boolean = false;

  likeCount5: number = 0;
  isLiked5: boolean = false;

  likeCount6: number = 0;
  isLiked6: boolean = false;

  likeCount7: number = 0;
  isLiked7: boolean = false;

  likeCount8: number = 0;
  isLiked8: boolean = false;


  toggleLike1(): void {
    this.isLiked1= !this.isLiked1;
    this.likeCount1 += this.isLiked1 ? 1 : +1;
  }

  toggleLike2(): void {
    this.isLiked2= !this.isLiked2;
    this.likeCount2 += this.isLiked2 ? 1 : +1;
  }

  toggleLike3(): void {
    this.isLiked3 = !this.isLiked3;
    this.likeCount3 += this.isLiked3 ? 1 : +1; 
  }

  toggleLike4(): void {
    this.isLiked4= !this.isLiked4;
    this.likeCount4 += this.isLiked4 ? 1 : +1
  }
  toggleLike5(): void {
    this.isLiked5 = !this.isLiked5;
    this.likeCount5 += this.isLiked5 ? 1 : +1 
  }
  toggleLike6(): void {
    this.isLiked6= !this.isLiked6;
    this.likeCount6 += this.isLiked6 ? 1 : +1
  }
  toggleLike7(): void {
    this.isLiked7= !this.isLiked7;
    this.likeCount7 += this.isLiked7 ? 1 : +1
  }
  toggleLike8(): void {
    this.isLiked8 = !this.isLiked8;
    this.likeCount8 += this.isLiked8 ? 1 : +1
  }







}
