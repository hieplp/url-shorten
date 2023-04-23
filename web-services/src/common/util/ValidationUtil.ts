export const isUrlValid = (url: string): boolean => {
  const urlPattern = /^(?:http[s]?:\/\/)?(?:www\.)?[\w.-]+\.\w+(?:\/.*)?$/;
  return urlPattern.test(url);
};