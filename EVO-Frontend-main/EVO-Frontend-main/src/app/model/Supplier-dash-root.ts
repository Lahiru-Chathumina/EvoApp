export class OLD_DashboardData {
    totalWorks: number;
    totalRatings: number;
    customers: number;
    bookingRequests: number;
    newMessages: number;
    totalMessages: number;
    unreadMessages: number;
    unreadEmails: number;

    constructor(data?: Partial<OLD_DashboardData>) {
        this.totalWorks = data?.totalWorks || 100;
        this.totalRatings = data?.totalRatings || 100;
        this.customers = data?.customers || 0;
        this.bookingRequests = data?.bookingRequests || 0;
        this.newMessages = data?.newMessages || 0;
        this.totalMessages = data?.totalMessages || 0;
        this.unreadMessages = data?.unreadMessages || 0;
        this.unreadEmails = data?.unreadEmails || 0;
    }
}
