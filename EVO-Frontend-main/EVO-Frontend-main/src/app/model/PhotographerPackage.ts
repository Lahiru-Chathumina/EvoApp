export default class OLD_PhotographerPackage{
  id:number;
  name:string;
  price:number;
  description:string;
  items:Array<string>;

  constructor(id:number, name:string, price:number, description:string, items:Array<string>) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.items = items;
  }

}
