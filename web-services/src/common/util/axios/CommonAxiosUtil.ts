import { AxiosError, AxiosResponse } from "axios";
import { BadRequestException } from "../../exception/BadRequestException";
import Localize from "../../constant/Localize";
import { UnauthorizedException } from "../../exception/UnauthorizedException";


// export default {
//
//   methods: {
//     success(response: AxiosResponse): any {
//       return response.data;
//     },
//
//     error  (error: AxiosError): any  {
//       // Handle specific error cases, e.g. token expiration, custom error codes, etc.
//       // ...
//
//       // Throw an error with the error message for generic error handling
//       switch (error.request.status) {
//         case 400:
//           throw new BadRequestException(error.message || Localize.Error.apiError);
//         case 401:
//           throw new UnauthorizedException(error.message || Localize.Error.apiError);
//         default:
//           throw new Error(error.message || Localize.Error.apiError);
//       }
//     }
//
//   }
//
// };

export const success = (response: AxiosResponse): any => {
  return response.data;
};

export const error = (error: AxiosError): any => {
  // Handle specific error cases, e.g. token expiration, custom error codes, etc.
  // ...


  // Throw an error with the error message for generic error handling
  switch (error.request.status) {
    case 400:
      throw new BadRequestException(error.message || Localize.Error.apiError);
    case 401:
      throw new UnauthorizedException(error.message || Localize.Error.apiError);
    default:
      throw new Error(error.message || Localize.Error.apiError);
  }
};
