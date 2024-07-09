import {IMonthlyData, IWeeklyData} from "../models/core/date/idatedata";


export const fakeWeeklyData: IWeeklyData[] = [
  {
    "label": "semaine 1",
    "number": 1,
    "tag": 'S1',
    "days": [
      {
        "data": 80.0,
        "label": "THURSDAY",
        "tag": "T",
        "date": new Date("2024-02-01")
      },
      {
        "data": 0.0,
        "label": "FRIDAY",
        "tag": "F",
        "date": new Date("2024-02-02")
      },
      {
        "data": 13.5,
        "label": "SATURDAY",
        "tag": "S",
        "date": new Date("2024-02-03")
      },
      {
        "data": 51.4,
        "label": "SUNDAY",
        "tag": "S",
        "date": new Date("2024-02-04")
      }
    ],
    "start_date": new Date("2024-02-01"),
    "end_date": new Date("2024-05-04")
  },
  {
    "label": "semaine 2",
    "number": 2,
    "tag": 'S2',
    "days": [
      {
        "data": 14.2,
        "label": "MONDAY",
        "tag": "M",
        "date": new Date("2024-02-05")
      },
      {
        "data": 0.0,
        "label": "TUESDAY",
        "tag": "T",
        "date": new Date("2024-02-06")
      },
      {
        "data": 0.0,
        "label": "WEDNESDAY",
        "tag": "W",
        "date": new Date("2024-02-07")
      },
      {
        "data": 0.0,
        "label": "THURSDAY",
        "tag": "T",
        "date": new Date("2024-02-08")
      },
      {
        "data": 0.0,
        "label": "FRIDAY",
        "tag": "F",
        "date": new Date("2024-02-09")
      },
      {
        "data": 0.0,
        "label": "SATURDAY",
        "tag": "S",
        "date": new Date("2024-02-10")
      },
      {
        "data": 0.0,
        "label": "SUNDAY",
        "tag": "S",
        "date": new Date("2024-02-11")
      }
    ],
    "start_date": new Date("2024-02-05"),
    "end_date": new Date("2024-02-11")
  },
  {
    "label": "semaine 3",
    "number": 3,
    "tag": 'S3',
    "days": [
      {
        "data": 0.0,
        "label": "MONDAY",
        "tag": "M",
        "date": new Date("2024-02-12")
      },
      {
        "data": 0.0,
        "label": "TUESDAY",
        "tag": "T",
        "date": new Date("2024-02-13")
      },
      {
        "data": 0.0,
        "label": "WEDNESDAY",
        "tag": "W",
        "date": new Date("2024-02-14")
      },
      {
        "data": 0.0,
        "label": "THURSDAY",
        "tag": "T",
        "date": new Date("2024-02-15")
      },
      {
        "data": 0.0,
        "label": "FRIDAY",
        "tag": "F",
        "date": new Date("2024-02-16")
      },
      {
        "data": 0.0,
        "label": "SATURDAY",
        "tag": "S",
        "date": new Date("2024-02-17")
      },
      {
        "data": 0.0,
        "label": "SUNDAY",
        "tag": "S",
        "date": new Date("2024-02-18")
      }
    ],
    "start_date": new Date("2024-02-12"),
    "end_date": new Date("2024-02-18")
  },
  {
    "label": "semaine 4",
    "number": 4,
    "tag": 'S4',
    "days": [
      {
        "data": 0.0,
        "label": "MONDAY",
        "tag": "M",
        "date": new Date("2024-02-19")
      },
      {
        "data": 0.0,
        "label": "TUESDAY",
        "tag": "T",
        "date": new Date("2024-02-20")
      },
      {
        "data": 0.0,
        "label": "WEDNESDAY",
        "tag": "W",
        "date": new Date("2024-02-21")
      },
      {
        "data": 0.0,
        "label": "THURSDAY",
        "tag": "T",
        "date": new Date("2024-02-22")
      },
      {
        "data": 0.0,
        "label": "FRIDAY",
        "tag": "F",
        "date": new Date("2024-02-23")
      },
      {
        "data": 0.0,
        "label": "SATURDAY",
        "tag": "S",
        "date": new Date("2024-02-24")
      },
      {
        "data": 0.0,
        "label": "SUNDAY",
        "tag": "S",
        "date": new Date("2024-02-25")
      }
    ],
    "start_date": new Date("2024-02-19"),
    "end_date": new Date("2024-02-25")
  },
  {
    "label": "semaine 5",
    "number": 5,
    "tag": 'S5',
    "days": [
      {
        "data": 0.0,
        "label": "MONDAY",
        "tag": "M",
        "date": new Date("2024-02-26")
      },
      {
        "data": 0.0,
        "label": "TUESDAY",
        "tag": "T",
        "date": new Date("2024-02-27")
      },
      {
        "data": 0.0,
        "label": "WEDNESDAY",
        "tag": "W",
        "date": new Date("2024-02-28")
      },
      {
        "data": 0.0,
        "label": "THURSDAY",
        "tag": "T",
        "date": new Date("2024-02-29")
      }
    ],
    "start_date": new Date("2024-02-26"),
    "end_date": new Date("2024-02-29")
  }
];

export const fakeMonthlyData: IMonthlyData[] =
  [
    {
      "data": 0.0,
      "label": "Janvier",
      "tag": "J",
      "month": 1,
      "year": 2024
    },
    {
      "data": 42.0,
      "label": "Février",
      "tag": "F",
      "month": 2,
      "year": 2024
    },
    {
      "data": 0.0,
      "label": "Mars",
      "tag": "M",
      "month": 3,
      "year": 2024
    },
    {
      "data": 24.0,
      "label": "Avril",
      "tag": "A",
      "month": 4,
      "year": 2024
    },
    {
      "data": 0.0,
      "label": "Mai",
      "tag": "M",
      "month": 5,
      "year": 2024
    },
    {
      "data": 7.0,
      "label": "Juin",
      "tag": "J",
      "month": 6,
      "year": 2024
    },
    {
      "data": 12.0,
      "label": "Juillet",
      "tag": "J",
      "month": 7,
      "year": 2024
    },
    {
      "data": 0.0,
      "label": "Aout",
      "tag": "A",
      "month": 8,
      "year": 2024
    },
    {
      "data": 0.0,
      "label": "Septembre",
      "tag": "S",
      "month": 9,
      "year": 2024
    },
    {
      "data": 0.0,
      "label": "Octobre",
      "tag": "O",
      "month": 10,
      "year": 2024
    },
    {
      "data": 0.0,
      "label": "Novembre",
      "tag": "N",
      "month": 11,
      "year": 2024
    },
    {
      "data": 0.0,
      "label": "Décembre",
      "tag": "D",
      "month": 12,
      "year": 2024
    }
  ]
