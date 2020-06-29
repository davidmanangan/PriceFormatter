import { Component, Input } from '@angular/core';

import { MatTableDataSource } from '@angular/material';

import { PriceFormat } from './model/priceformat'
import { PriceFormatterService } from './service/price-formatter.service'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'priceformatter-ui';

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
    console.log("User Input: ")
    console.log(this.rawPriceInput);

    await this.priceFormatterService
            .process(new PriceFormat(this.rawPriceInput,this.formatInput,this.dplInput,this.fplInput,this.scaleInput))
            .then((result:PriceFormat) =>{

              this.priceFormatArray.push(result);
              this.dataSource.data = this.priceFormatArray;

              this.rawPriceInput = "";
              this.formatInput = "";
              this.scaleInput = "";
              this.dplInput = "";
              this.fplInput = "";
            })
      ;

  }

}
