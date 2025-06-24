export default class OLD_User {
    userId: number;
    userName: string;
    userType: string;
    password: string;
    email: string;
    registeredDate: Date;

    constructor(userId: number, userName: string, userType: string, password: string, email: string, registeredDate: Date) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.password = password;
        this.email = email;
        this.registeredDate = registeredDate;
    }
}
