import {Client} from './client.model';

export class Dish {
  id: number;
  name: string;
  type: string;
  description: string;
  timeOfCooking: number;
  dateOfCreate: string;
  image: string;
  author: Client;
}
