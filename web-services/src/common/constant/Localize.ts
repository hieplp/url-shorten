export default class Localize {
  public static readonly Register = {
    invalidUsername: "Username must be at least 3 characters",
    invalidPassword: "Password must be at least 8 characters",
    invalidConfirmPassword: "Passwords do not match",
    duplicatedUsername: "Username already exists"
  };

  public static readonly Login = {
    invalidUsername: "Username must be at least 3 characters",
    invalidPassword: "Password must be at least 8 characters",
    invalidCredentials: "Invalid username or password"
  };

  public static readonly Url = {
    invalidUrl: "Invalid URL. Please enter a valid URL.",
    invalidAlias: "Alias must be at least 6 characters",
    duplicatedAlias: "Alias already exists",
    //
    active: "Active",
    inactive: "Inactive",
    expired: "Expired",
    //
    updateSuccess: "Update successfully"
  };

  public static readonly Error = {
    unknownError: "Unknown error",
    apiError: "An error occurred while making the request",
    unauthorized: "Unauthorized"
  };

}