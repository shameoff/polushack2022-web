import { validationResult } from 'express-validator';
import authService from '../service/auth.service.js';
import userService from '../service/user.service.js';
import HttpStatus from '../utils/HttpStatus.js';

class AuthController {
  async register(req, res) {
    try {
      const validationErrors = validationResult(req);
      if (!validationErrors.isEmpty()) {
        return res
          .status(HttpStatus.BAD_REQUEST)
          .send('Registration error', validationErrors);
      }

      const result = await userService.create(req.body);
      res.status(HttpStatus.CREATED).send(result);
    } catch (err) {
      res.status(err.statusCode).send(err.message);
    }
  }

  async login(req, res) {
    // req.body = { email: "ssafds@mail.ru", password: "12389adsyf"}
    try {
      const token = await authService.login(req.body);
      res.status(HttpStatus.ACCEPTED).send(token);
    } catch (err) {
      res.status(err.statusCode).send(err.message);
    }
  }

  async logout(req, res) {}
}

const authController = new AuthController();
export default authController;
