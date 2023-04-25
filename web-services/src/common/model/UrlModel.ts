export default interface UrlModel {
  urlId: string,
  longUrl: string,
  shortUrl: string,
  alias: string,
  expiredAt: number,
  status: number,
  createdAt: number,
  createdBy: string,
  modifiedAt: number,
  modifiedBy: string,
}