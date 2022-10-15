import BaseError from '../error/BaseError.js';
import HttpStatus from './HttpStatus.js';

export default function responseWithError(res, err) {
  if (err instanceof BaseError) {
    return res.status(err.statusCode).send(err.message);
  }

  return res.status(HttpStatus.INTERNAL_SERVER_ERROR).send(err.stack);
}
