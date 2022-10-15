import jwt from 'jsonwebtoken';
import userService from './user.service.js';

class AuthService {
  generateAccessToken(id, role) {
    const payload = { id, role };
    return jwt.sign(payload, process.env.SECRET, { expiresIn: '14h' });
  }

  async register(reqBody, role) {
    if (role) reqBody.role = role;

    const user = await userService.create(req.body);
    user.token = this.generateAccessToken(user.id, user.role);

    return user;
  }

  async login(reqBody) {
    try {
      const user = await userService.getUserByEmail(reqBody.email);
      const isValid = await userService.isPasswordValid(reqBody);

      if (!isValid)
        res.status(HttpStatus.BAD_REQUEST).send('The password is incorrect');

      return this.generateAccessToken(user.id, user.role);
    } catch (err) {
      throw err;
    }
  }
}

export default new AuthService();

// TODO: Список характеристик
