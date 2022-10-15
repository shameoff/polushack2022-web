import HttpStatus from '../utils/HttpStatus';
import BaseError from './BaseError';

export default class DoesNotExistError extends BaseError {
  constructor(
    statusCode = HttpStatus.NOT_FOUND,
    message = `Object does not exists`,
  ) {
    this.name = 'DoesNotExistError';
    this.message = message;
    this.statusCode = statusCode;
  }
}
