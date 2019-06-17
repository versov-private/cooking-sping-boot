import {Role} from "./role.model";

export class Client {
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
