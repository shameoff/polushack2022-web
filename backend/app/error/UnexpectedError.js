import HttpStatus from '../utils/HttpStatus';
import BaseError from './BaseError';

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
