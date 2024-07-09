import {ISidenavGroupItem, ISidenavItem} from "../models/core/sidenav";


export const navbarData : ISidenavItem[] = [
  {
    routerLink: '/user-space/home',
    icon: 'fa-solid fa-chart-line',
    label: 'Home'
  },
  {
    routerLink: '/user-space/modules',
    // icon: 'fa-solid fa-chart-simple',
    icon: 'pi pi-inbox',
    label: 'Modules',
  },
  {
    routerLink: '/user-space/customers',
    icon: 'pi pi-users',
    label: 'Clients'
  },
  {
    routerLink: '/user-space/supports',
    icon: 'fa fa-headset',
    label: 'Reports'
  },
  {
    routerLink: '/user-space/comments',
    icon: 'far fa-comments',
    label: 'Ratings and Reviews'
  }
]

export const appSidebarData: ISidenavGroupItem[] = [
  {
    group: 'Dashboard',
    items: navbarData,
    subTitle: 'Section'
  }
]

export const profileNavData: ISidenavItem[] = [
  {
    routerLink: '/user-space/home',
    icon: 'fa-solid fa-chart-line',
    label: 'Profil'
  },
  {
    routerLink: '/item',
    icon: 'fa-solid fa-chart-simple',
    label: 'Reports',
  }
]

export const reportNavData: ISidenavGroupItem[] = [
  {
    group: 'Filter',
    subTitle: '',
    items: [
      {
        icon: 'fa-solid fa-list-check',
        label: 'All',
        data: 'all'
      },
      {
        icon: 'fa-solid fa-check-double',
        label: 'Closed',
        data: 'closed'
      },
      {
        icon: 'fa-solid fa-spinner',
        label: 'Progress',
        data:'in progress'
      }
    ]
  }
]
