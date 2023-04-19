import MD5 from "crypto-js/md5";
import { JSEncrypt } from "jsencrypt";
import { Config } from "../../config";

export default class EncryptUtil {
  public static encryptPassword(password: string): string {
    const sign = new JSEncrypt();
    sign.setPublicKey(Config.passwordPublicKey);
    return sign.encrypt(MD5(password).toString()).toString();
  }
}