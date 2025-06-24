import { ProfilePreviousWork } from "./ProfilePreviousWork";

export class ProfilePreviousWorkImage {
  profilePreviousWorkImageId: number;
  profilePreviousWork: ProfilePreviousWork;
  imageUrl: string;

  constructor(
    profilePreviousWorkImageId: number,
    profilePreviousWork: any,
    imageUrl: string
  ) {
    this.profilePreviousWorkImageId = profilePreviousWorkImageId;
    this.profilePreviousWork = profilePreviousWork;
    this.imageUrl = imageUrl;
  }
}
