export interface ISidenavItem{
  routerLink?: string,
  icon?: string,
  iconColor?: string,
  label: string,
  expanded?: boolean,
  items?: ISidenavItem[],
  active?: boolean,
  data?: any
}

export interface ISidenavGroupItem{
  group: string,
  subTitle: string,
  items: ISidenavItem[]
}
