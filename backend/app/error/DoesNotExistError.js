import HttpStatus from '../utils/HttpStatus.js';
import BaseError from './BaseError.js';

export default class DoesNotExistError extends BaseError {
  constructor(
    statusCode = HttpStatus.NOT_FOUND,
    message = `Object does not exists`,
  ) {
    super(statusCode, message);

    this.name = 'DoesNotExistError';
    this.message = message;
    this.statusCode = statusCode;
  }
}
