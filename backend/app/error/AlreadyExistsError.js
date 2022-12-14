import HttpStatus from '../utils/HttpStatus.js';
import BaseError from './BaseError.js';

export default class AlreadyExistError extends BaseError {
  constructor(
    statusCode = HttpStatus.CONFLICT,
    message = `Object already exists`,
  ) {
    super(statusCode, message);
    
    this.name = 'AlreadyExistsError';
  }
}
