export class AgendaTask {
    taskId: number;
    taskName: string;
    startTime: string;
    endTime: string;

    constructor(taskId: number, taskName: string, startTime: string, endTime: string){
        this.taskId = taskId;
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

