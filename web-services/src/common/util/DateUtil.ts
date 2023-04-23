export const currentDatetime = () => {
  const now: Date = new Date();
  const timezoneOffset = now.getTimezoneOffset() * 60000; // Convert offset from minutes to milliseconds
  // @ts-ignore
  return new Date(now - timezoneOffset).toISOString().slice(0, -1); // Remove trailing 'Z' for local timezone
};