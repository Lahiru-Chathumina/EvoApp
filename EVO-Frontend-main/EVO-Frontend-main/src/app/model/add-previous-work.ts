export default class OLD_addPreviousWork{
    workId:number;
    profileId:number;
    title:string;
    category:string;
    description:string;
    completionDate:string;
    imageUrl:string;

    constructor(workId:number,profileId:number,title:string,category:string,description:string,completionDate:string,imageUrl:string){
        this.workId=workId;
        this.profileId=profileId;
        this.title=title;
        this.category=category;
        this.description=description;
        this.completionDate=completionDate;
        this.imageUrl=imageUrl;
    }
  }
