export default class OLD_Venue{

  id:number;
  supplierId: number;
  location: string ;
  type:string;
  name:string
  about:string;
  price:number;
  image:string;
  capacity:number;

  constructor(  id:number,
    supplierId: number,
    location: string ,
    type:string,
    name:string,
    about:string,
    price:number,
    image:string,
    capacity:number){

      this.id=id;
      this.supplierId=supplierId;
      this.name = name ;
      this.location=location;
      this.type= type;
      this.price=price;
      this.image=image;
      this.about = about;
      this.capacity =capacity;
  }
}

