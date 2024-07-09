export interface AuthResponseModel {
  token: string,
  user: UserModel
}

export interface UserModel{
  id: number,
  email: string
}
