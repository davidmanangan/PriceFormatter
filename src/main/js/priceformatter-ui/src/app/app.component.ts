import { Component, Input } from '@angular/core';

import { MatTableDataSource } from '@angular/material';

import { PriceFormat } from './model/priceformat'
import { PriceFormatterService } from './service/price-formatter.service'

interface DisplayFormat {
  value: number;
  viewValue: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'priceformatter-ui';

  formats: DisplayFormat[] = [
    {value: 1, viewValue: 'Decimal'},
    {value: 100, viewValue: 'Percentage'},
    {value: 1000, viewValue: 'Per Mile'},
    {value: 10000, viewValue: 'Basis Point'}
  ];

  displayedColumns: string[] = ['rawPrice','format','dpl','fpl','scale','bf','dp','fp'];
  
  priceFormatArray:PriceFormat[] = [];

  dataSource = new MatTableDataSource<PriceFormat>(this.priceFormatArray);  

  @Input()
  rawPriceInput:string

  @Input()
  formatInput:string

  @Input()
  scaleInput:string

  @Input()
  dplInput:string

  @Input()
  fplInput:string

  constructor(private priceFormatterService:PriceFormatterService){}

  public async formatPrice(){

    await this.priceFormatterService
            .process(new PriceFormat(this.rawPriceInput,this.formatInput,this.dplInput,this.fplInput,this.scaleInput))
            .then((result:PriceFormat) =>{
              
              result.bf = (result.bf != "0")?result.bf:"";
              result.dp = (result.dp != "0")?result.dp:"";
              result.fp = (result.fp != "0")?result.fp:"";
              
              this.priceFormatArray.push(result);
              this.dataSource.data = this.priceFormatArray;

              this.rawPriceInput = "";
              this.formatInput = "";
              this.scaleInput = "";
              this.dplInput = "";
              this.fplInput = "";
            });
  }
}
