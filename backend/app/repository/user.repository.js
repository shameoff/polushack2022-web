import bcrypt from 'bcrypt';
import pool from '../config/db.config';
import AlreadyExistsError from '../error/AlreadyExistsError.js';
import UnexpectedError from '../error/UnexpectedError.js';

class UserRepository {
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

    const hash = await bcrypt.hash(user.password, 10);

    try {
      const result = await pool.query(
        `INSERT INTO public.user VALUES($1, $2, $3, $4)`,
        [Object.values({ user, password: hash })],
      );

      return result.rows;
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
    
    if (!result) return null;

    return result.rows[0];
  }

  async isPasswordValid(user) {
    return bcrypt.compareSync(user.password, await this.getUserByEmail(user.email));
  }
}

const userRepository = new UserRepository();

export default userRepository;
