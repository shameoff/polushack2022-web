export default class BaseError extends Error {
  constructor(statusCode, message) {
    super(description);
  
    Object.setPrototypeOf(this, new.target.prototype);

    this.name = 'BaseError';
    this.statusCode = statusCode;
    this.message = message;

    Error.captureStackTrace(this);
  }
}
