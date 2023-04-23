import TokenModel from "../model/TokenModel";

export const saveCookie = (name: string, value: string, time: number): void => {
  const date = new Date(time);
  let expires = "; expires=" + date.toUTCString();
  document.cookie = name + "=" + (value || "") + expires + "; path=/";
};

export const saveToken = (name: string, token: TokenModel): void => {
  saveCookie(name, token.token, token.expiredAt);
};

export const deleteCookie = (name: string): void => {
  document.cookie = name + "=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
};

export const getCookie = (name: string): string | null => {
  const cookies = document.cookie.split("; ");
  for (let i = 0; i < cookies.length; i++) {
    const cookie = cookies[i].split("=");
    if (cookie[0] === name) {
      return cookie[1];
    }
  }
  return null; // Cookie not found
};

export const doesCookieExist = (name: string): boolean => {
  return getCookie(name) !== null;
};
