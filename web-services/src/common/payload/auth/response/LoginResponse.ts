import UserModel from "../../../model/UserModel";
import TokenModel from "../../../model/TokenModel";

export interface LoginResponse {
  user: UserModel;
  accessToken: TokenModel;
  refreshToken: TokenModel;
}