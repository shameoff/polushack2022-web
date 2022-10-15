import jwt from 'jsonwebtoken';
import HttpStatus from '../utils/HttpStatus.js';

export default function authMiddleware(req, res, next) {
  if (
    req.method === 'OPTIONS' ||
    req.path === '/user/register' ||
    req.path === '/user/login'
  ) {
    return next();
  }

  try {
    if (!req.headers.authorization) {
      return res.redirect('/user/register');
    }

    const nameAndToken = req.headers.authorization.split(' ');
    const bearer = nameAndToken[0];
    const token = nameAndToken[1];

    if (bearer !== 'Bearer') {
      return res
        .status(HttpStatus.BAD_REQUEST)
        .send('Bad Authorization header format');
    }

    if (!token) {
      return res.status(HttpStatus.FORBIDDEN).send('Token not present');
    }

    const decodedToken = jwt.verify(token, process.env.SECRET);
    req.userToken = decodedToken;

    next();
  } catch (err) {
    console.error(err);
    res.status(HttpStatus.FORBIDDEN).send('User is unauthorized');
  }
}
