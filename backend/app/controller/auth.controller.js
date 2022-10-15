import { validationResult } from 'express-validator';
import authService from '../service/auth.service.js';
import HttpStatus from '../utils/HttpStatus.js';
import Role from '../utils/Role.js';

class AuthController {
  _checkValidationResult(req, res) {
    const validationErrors = validationResult(req);
    if (!validationErrors.isEmpty()) {
      return res
        .status(HttpStatus.BAD_REQUEST)
        .send('Registration error', validationErrors);
    }
  }

  async register(req, res) {
    try {
      this._checkValidationResult(req, res);

      const user = await authService.register(req.body);
      res.status(HttpStatus.CREATED).send(user);
    } catch (err) {
      res.status(err.statusCode).send(err.message);
    }
  }

  async registerByRole(req, res) {
    try {
      this._checkValidationResult(req, res);

      const role = Role[req.query.role.toLowerCase()];
      const user = await authService.register(req.body, role);

      res.status(HttpStatus.CREATED).send(user);
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

export default new AuthController();
