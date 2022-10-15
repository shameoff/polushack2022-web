import HttpStatus from '../utils/HttpStatus';
import BaseError from './BaseError';

export default class AlreadyExistsError extends BaseError {
  constructor(
    statusCode = HttpStatus.CONFLICT,
    message = `Object already exists`,
  ) {
    this.name = 'AlreadyExistsError';
    this.message = message;
    this.statusCode = statusCode;
  }
}
