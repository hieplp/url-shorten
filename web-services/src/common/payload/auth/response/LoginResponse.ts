import UserModel from "../../../model/UserModel";
import TokenModel from "../../../model/TokenModel";

export default interface LoginResponse {
  user: UserModel;
  accessToken: TokenModel;
  refreshToken: TokenModel;
}