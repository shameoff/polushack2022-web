import userService from '../service/user.service.js';
import HttpStatus from '../utils/HttpStatus.js';
import responseWithError from '../utils/responseWithError.js';
import Role from '../utils/Role.js';

class UserController {
  async getAll(req, res) {
    const users = await userService.getAll();

    res.status(HttpStatus.OK).send(users);
  }

  async getUserByEmail(req, res) {
    try {
      const user = await userService.getUserByEmail(req.query.email);

      res.status(HttpStatus.OK).send(user);
    } catch (err) {
      responseWithError(res, err);
    }
  }

  async getUserById(req, res) {
    try {
      console.log(req.params.id);
      const user = await userService.getUserById(req.params.id);

      res.status(HttpStatus.OK).send(user);
    } catch (err) {
      responseWithError(res, err);
    }
  }

  async getUsersByRole(req, res) {
    try {
      const user = await userService.getUserByRole(Role.getRoleByName(req.query.role));
      res.status(HttpStatus.ACCEPTED).send(user);
    } catch (err) {
      responseWithError(res, err);
    }
  }

  async getUserByStatus(req, res) {
    try {
      const user = userService.getUsersByStatus(req.query.status);
      res.status(HttpStatus.ACCEPTED).send(user);
    } catch (err) {
      responseWithError(res, err);
    }
  }
}

export default new UserController();
