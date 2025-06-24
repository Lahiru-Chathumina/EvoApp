 export default class OLD_PreviousWork{
  workId:number;
  ProfileId:number;
  title:string;
  description:string;
  completionDate:string;
  imageUrls:string;
  clientName:string;

  photographerName:string;

  constructor(workId:number,ProfileId:number,title:string,description:string,completionDate:string,imageUrls:string,clientName:string,  photographerName:string
  ){
    this.workId= workId;
    this.ProfileId=ProfileId;
    this.title=title;
    this.description=description;
    this.completionDate=completionDate;
    this.imageUrls=imageUrls;
    this.clientName=clientName;
    this.photographerName=photographerName;
    ;

}
 }
