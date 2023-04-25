export default interface UpdateUrlByAuthRequest {
  urlId: string,
  longUrl: string,
  alias: string,
  expiredAt: number,
}