export class UserModel {
  id: number;
  username: string;
  password: string;
  modile: string;
  authorities?: JSON;
  token?: string;
  enabled?: boolean;
}
