import TokenModel from "../model/TokenModel";

export const saveCookie = (name: string, value: string, time: number): void => {
  const date = new Date(time);
  let expires = "; expires=" + date.toString();
  document.cookie = name + "=" + (value || "") + expires + "; path=/";
};

export const saveToken = (name: string, token: TokenModel): void => {
  saveCookie(name, token.token, token.expiredAt);
};

export const deleteCookie = (name: string): void => {
  document.cookie = name + "=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
};

export const getCookie = (name: string): string | null => {
  let nameEQ = name + "=";
  let ca = document.cookie.split(";");
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == " ") c = c.substring(1, c.length);
    if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
  }

  return null;
};

export const doesCookieExist = (name: string): boolean => {
  return getCookie(name) !== null;
};
