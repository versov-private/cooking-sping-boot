import {Role} from "./role.model";

export class User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  dateOfBirth: string;
  sex: string;
  avatar: string;
  username: string;
  roles: Role[];
}
