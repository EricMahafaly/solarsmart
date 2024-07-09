import {DateHelper} from "../shared/helper/date-helper";
import {ISelectOption} from "../models/core/iselect";
import {formatDate} from "@angular/common";

// export function getDateTypeFilterOptions(): ISelectOption[]{
//   return [
//     {
//       label: 'Cette année',
//       key: 'month_by_year',
//       value: new Date().getFullYear(),
//       selected: true
//     },
//     {
//       label: 'Ce mois',
//       key: 'week_by_month',
//       value: new Date().getMonth()
//     }
//   ];
// }

export function getPlagesDatesOptions(): ISelectOption[]{
  const dates: Date[] = DateHelper.getAllHourInDate();
  let plagesDates: ISelectOption[] = []

  let i = 0;

  const date: Date = new Date();

  while (i < dates.length - 1){
    let select : boolean = DateHelper.isBetween(date, dates[i], dates[i+1])
    plagesDates.push({
      label: generateFilterDateLabel(dates[i], dates[i+1]),
      value: [dates[i], dates[i+1]],
      key: '',
      selected: select
    })

    i++;
  }

  plagesDates.push({
    label: generateFilterDateLabel(dates[dates.length - 1], dates[0]),
    value: [dates[dates.length - 1], dates[0]],
    key:'',
    selected: DateHelper.isBetween(date, dates[dates.length - 1], dates[0])
  })

  return plagesDates;
}

export function getActiveOption(options: ISelectOption[]): ISelectOption{

  let size = options.length;
  for (let i = 0; i < size; i++){
    if (options[i].selected) return options[i];
  }

  return options[0];
}

// function isBet

function generateFilterDateLabel(date: Date, endDate: Date): string {
  return `${formatDate(date, 'HH:mm', "en-US")} à ${formatDate(endDate, 'HH:mm', "en-US")}`
}
