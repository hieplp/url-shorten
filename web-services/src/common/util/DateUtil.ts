export const currentDatetime = () => {
  const now: Date = new Date();
  const timezoneOffset = now.getTimezoneOffset() * 60000; // Convert offset from minutes to milliseconds
  // @ts-ignore
  return new Date(now - timezoneOffset).toISOString().slice(0, -1); // Remove trailing 'Z' for local timezone
};

export const formatDatetime = (datetime: number): string => {
  const date = new Date(datetime);
  const year = date.getFullYear().toString();
  const month = (date.getMonth() + 1).toString().padStart(2, "0");
  const day = date.getDate().toString().padStart(2, "0");
  const hours = date.getHours().toString().padStart(2, "0");
  const minutes = date.getMinutes().toString().padStart(2, "0");
  return `${year}-${month}-${day} ${hours}:${minutes}`;
};

