import HttpStatus from '../utils/HttpStatus.js';
import BaseError from './BaseError.js';

export default class UnexpectedError extends BaseError {
  constructor(
    statusCode = HttpStatus.CONFLICT,
    message = `This error was unexpected`,
  ) {
    this.name = 'UnexpectedError';
    this.message = message;
    this.statusCode = statusCode;
  }
}
