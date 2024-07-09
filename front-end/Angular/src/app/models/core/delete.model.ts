import {ModuleOtherInfoDetailModel, ModuleOtherInfoModel} from "../api/info.model";

export interface DeleteTree<T = any>{
  parent?: DeleteTree,
  data: T,
  children?: DeleteTree[]
  childOnly?: boolean

}

export interface DeleteModuleInfoModel{
  parent: ModuleOtherInfoModel,
  data: ModuleOtherInfoDetailModel[]
  childOnly: boolean
}

export function haveParent(deletes: DeleteModuleInfoModel, parent: ModuleOtherInfoModel): boolean{
  // return !!(deletes.parent && deletes.parent === parent);
  return (deletes.parent && deletes.parent === parent);
}

export function existingParent(deletes: DeleteModuleInfoModel[], parent: ModuleOtherInfoModel): boolean{
  for (let delete1 of deletes) {
    if (haveParent(delete1, parent)) return true;
  }
  return false;
}
