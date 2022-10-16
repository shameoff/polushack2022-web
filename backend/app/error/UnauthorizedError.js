import HttpStatus from '../utils/HttpStatus.js';
import BaseError from './BaseError.js';

export default class UnauthorizedError extends BaseError {
  constructor(
    statusCode = HttpStatus.UNAUTHORIZED,
    message = `User is unauthorized`,
  ) {
    super(statusCode, message);

    this.name = 'UnauthorizedError';
    this.message = message;
    this.statusCode = statusCode;
  }
}
