import BaseError from '../error/BaseError';
import HttpStatus from './HttpStatus';

export default function responseWithError(res, err) {
  if (err instanceof BaseError) {
    return res.status(err.statusCode).send(err.message);
  }

  return res
    .status(HttpStatus.INTERNAL_SERVER_ERROR)
    .send('Something went wrong');
}
