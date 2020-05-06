import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'rate'
})
export class RatePipe implements PipeTransform {

  transform(value: number): string {
    if (value == 0) {
      return '0.00%';
    } else {
      return value > 0 ? (value*100).toFixed(2) + "%": (value*100).toFixed(2) + "%";
    }
  }

}
