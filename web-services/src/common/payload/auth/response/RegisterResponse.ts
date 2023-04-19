import UserModel from "../../../model/UserModel";
import TokenModel from "../../../model/TokenModel";

export interface RegisterResponse {
  user: UserModel;
  accessToken: TokenModel;
  refreshToken: TokenModel;
}