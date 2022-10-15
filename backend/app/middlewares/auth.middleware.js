import jwt from 'jsonwebtoken';
import HttpStatus from '../utils/HttpStatus.js';

export default function authMiddleware(req, res, next) {
  if (req.method === 'OPTIONS') {
    next();
  }

  try {
    const nameAndToken = req.headers.authorization.split(' ');
    const bearer = nameAndToken[0];
    const token = nameAndToken[1];

    if (bearer !== 'Bearer') {
      res
        .status(HttpStatus.BAD_REQUEST)
        .send('Bad Authorization header format');
    }

    if (!token) {
      res.status(HttpStatus.FORBIDDEN).send('User is unauthorized');
    }

    const decodedToken = jwt.verify(token, process.env.SECRET);
    req.userToken = decodedToken;
    
    next();
  } catch (err) {
    console.error(err);
    res.status(HttpStatus.FORBIDDEN).send('User is unauthorized');
  }
}
