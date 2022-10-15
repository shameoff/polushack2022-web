import bcrypt from 'bcrypt';
import pool from '../config/db.config.js';
import AlreadyExistsError from '../error/AlreadyExistsError.js';
import DoesNotExistsError from '../error/DoesNotExistError.js';
import UnexpectedError from '../error/UnexpectedError.js';

class UserService {
  async isUserExists(email) {
    const result = await pool.query(
      `SELECT EXISTS(SELECT * FROM public.user WHERE email=$1)`,
      [email],
    );

    return result.rows[0].exists;
  }

  async create(user) {
    if (this.isUserExists(email))
      throw new AlreadyExistsError(`User with email ${email} already exists`);

    const hash = bcrypt.hashSync(user.password, 10);

    try {
      const result = await pool.query(
        `INSERT INTO public.user VALUES($1, $2, $3, $4)`,
        [Object.values({ user, password: hash })],
      );

      const response = result.rows[0];

      return { ...[response.id, response.firstName, response.lastName, response.email] };
    } catch (err) {
      throw new UnexpectedError(
        'Something went wrong while trying to create user',
      );
    }
  }

  async getUserByEmail(email) {
    const result = await pool.query(
      'SELECT * FROM public.user WHERE email=$1',
      [email],
    );
    
    if (!result) throw new DoesNotExistsError(`User with email ${email} does not exists`);

    return result.rows[0];
  }

  async isPasswordValid(user) {
    return bcrypt.compareSync(user.password, await this.getUserByEmail(user.email));
  }
}

export default new UserService();
