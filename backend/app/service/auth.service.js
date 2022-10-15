import jwt from 'jsonwebtoken';
import userService from './user.service.js';

class AuthService {
  generateAccessToken(id, role) {
    const payload = { id, role };
    return jwt.sign(payload, process.env.SECRET, { expiresIn: '14h' });
  }

  async login(reqBody) {
    try {
      const user = await userService.getUserByEmail(reqBody.email);
      const isValid = await userService.isPasswordValid(reqBody);

      if (!isValid)
        res.status(HttpStatus.BAD_REQUEST).send('The password is incorrect');

      return this.generateAccessToken(user.id);
    } catch (err) {
      throw err;
    }
  }
}

export default new AuthService();
