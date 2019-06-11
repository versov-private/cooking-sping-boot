export class SignUpInfo {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  roles: string[];
  password: string;
  sex: string;

  constructor(firstName: string, lastName: string, username: string, email: string, password: string, sex: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = ["user"];
    this.sex = sex;
  }
}
