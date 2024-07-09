import {ISelectOption} from "../models/core/iselect";
import {FilterDate} from "../models/core/date/filter-date";

export const basicFilterDateNow: ISelectOption[] = [
    {
        label: 'This year',
        key: FilterDate.MONTH_BY_YEAR,
        value: new Date().getFullYear(),
        selected: true
    },
    {
        label: 'This month',
        key: FilterDate.WEEK_BY_MONTH,
        value: new Date().getMonth()
    }
]
