export default class Session{
    title: string;
    type: string;
    startTime: string;
    endTime: string;

    constructor(title: string,type: string,startTime: string,endTime: string){
        this.title=title;
        this.type=type;
        this.startTime=startTime;
        this.endTime=endTime;
    }
}