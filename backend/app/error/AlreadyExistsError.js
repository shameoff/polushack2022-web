import HttpStatus from '../utils/HttpStatus.js';
import BaseError from './BaseError.js';

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
