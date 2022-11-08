import { Component, OnInit,Input } from '@angular/core';
import { CategoryWithData } from 'src/app/models/CategoryWithData';

@Component({
  selector: 'app-category-view',
  templateUrl: './category-view.component.html',
  styleUrls: ['./category-view.component.css']
})
export class CategoryViewComponent implements OnInit {
  @Input() currentCategoryWithData!:CategoryWithData;
  constructor() { }

  ngOnInit(): void {
  }

}
