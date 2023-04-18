export const CONFIG = {
  API_HOST: import.meta.env.API_HOST || "http://localhost:9900",

  PASSWORD_PUBLIC_KEY: import.meta.env.PASSWORD_PUBLIC_KEY ||
    `-----BEGIN PUBLIC KEY-----
     MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxrhiHoJj4Rr4WqbKc7TB
     S5/gsx08PTnBZSD320lVtQeuakPXyojp1OVSWrxz/2k8Scn7DjGZlgPfHgh8/Rmr
     rHuo42L283yZTXfiEnua0py9G2zOunGPCKWABDJl67JUJAmjnjRLobH5iexFGqrw
     lFQ+WZf3gnTbPaFjCmfpypIU25ixXfaV/j9G2Z7lC0q5FnqX6QqbJ4AJGTHZysgE
     1ueH5sw7e3gDrDCjngUjT67aAzRaT0ZkoF6qS3j9M+mV8P/eavxyM+FFRRvaxYDa
     szXp6OvYctoTHwvALaCux7m8gkSeslIMgD4T745Yfjo/Rn8aRfRK96WAfsjANc21
     TwIDAQAB
     -----END PUBLIC KEY-----`,

  TOKEN_PUBLIC_KEY: import.meta.env.TOKEN_PUBLIC_KEY ||
    `-----BEGIN PUBLIC KEY-----
    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqssGZwp4q9grDkztlx9P
    pMz6A/WwVqblFihNnMH6MsZ+4f1E4D4xbZLFFWPRPdpJCacAGP13le9/oPzsHY9j
    yL04U0ke/4HpxN45bzHJf/j3QoJYUqdzSMjiU7lxEWlbkQgqsab5tGLcnQUExQ+A
    JIRZMoQzABZSbO2CApxdvu1uxiK6CtCO4XePASOtIWhpbCFhuA2xhTjuZdoDd2x4
    4zVtoxwSU0TMrvUpZzonG3mvMF43hItvcxW1RiZD2NsnlpZvSzKAWqQR7jH92azH
    5askEsF5a4mFQuwpKO4vUVac8kDzN078RjXeR8dBL0sCcfgyFg8qWOE91vIThRS8
    6wIDAQAB
    -----END PUBLIC KEY-----`
};
