import { UserType } from "../../../utils/UserType";

export type Blog = {
    id?: number,
    title: string,
    author_name: string,
    author_type: UserType,
    profile_image_url: string,
    content: string,
    liked?: boolean,
    bookmarked?: boolean,
    createdAt: string,
    like_count: number
    bookmark_count: number,
    image_url?: string,
}
