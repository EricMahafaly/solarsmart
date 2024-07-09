export interface CustomerInfoModel{
  id: number,
  email: string,
  name: string,
  lastName: string,
  image?: string
  address: string,
  postalCode: string,
}

export interface CustomerStatistic {
  count: number,
  countLast: number,
  percentageToLast: number
}
