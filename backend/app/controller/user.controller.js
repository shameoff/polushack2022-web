import HttpStatus from '../utils/HttpStatus';
import userService from '../service/user.service';
import responseWithError from '../utils/responseWithError';
import Role from '../utils/Role';

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
      const user = await userService.getUserById(req.query.id);

      res.status(HttpStatus.OK).send(user);
    } catch (err) {
      responseWithError(res, err);
    }
  }

  async getUserByRole(req, res) {
    try {
      const user = await userService.getUserByRole(Role.getRoleByName(req.query.role));

    } catch (err) {
      responseWithError(res, err);
    }
  }

  async getUserByStatus(req, res) {
    try {
      
    } catch (err) {
      
    }
  }
}

export default new UserController();
