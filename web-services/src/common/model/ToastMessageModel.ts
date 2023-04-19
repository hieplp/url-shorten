export default interface ToastMessageModel {
  id: string;
  message: string;
  type: "success" | "error" | "info";
}