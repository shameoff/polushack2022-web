import UnauthorizedError from '../error/UnauthorizedError';
import HttpStatus from '../utils/HttpStatus';
import Role from '../utils/Role';

export default function roleMiddleware(...roles) {
  return function (req, res, next) {
    if (req.method === 'OPTIONS') {
      next();
    }

    try {
      if (!req.userToken) throw new UnauthorizedError();

      const { role: userRole } = req.userToken;
      const hasRole = false;

      if (userRole !== Role.ADMIN) {
        for (const role of roles) {
          if (role === userRole) {
            hasRole = true;
            break;
          }
        }

        if (!hasRole) {
          res
            .status(HttpStatus.UNAUTHORIZED)
            .send("You don't have required permissions");
        }
      }

      next();
    } catch (err) {
      console.error(err);
      res.status(HttpStatus.FORBIDDEN).send('User is unauthorized');
    }
  };
}
