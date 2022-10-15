import bcrypt from 'bcrypt';
import pool from '../config/db.config.js';
import AlreadyExistsError from '../error/AlreadyExistsError.js';
import DoesNotExistsError from '../error/DoesNotExistError.js';
import UnexpectedError from '../error/UnexpectedError.js';

class UserService {
  async isUserExists(email) {
    console.log(1);
    const result = await pool.query(
      `SELECT EXISTS(SELECT * FROM public.user WHERE email=$1)`,
      [email],
    );
    console.log(2);

    return result.rows[0].exists;
  }

  async create(user) {
    if (this.isUserExists(user.email))
      throw new AlreadyExistsError(`User with email ${user.email} already exists`);

    const hash = bcrypt.hashSync(user.password, 10);

    try {
      const result = await pool.query(
        `INSERT INTO public.user (first_name, last_name, email, password, role) VALUES($1, $2, $3, $4)`,
        [...Object.values({ user, password: hash })],
      );

      const response = result.rows[0];

      return {
        ...[
          response.id,
          response.first_name,
          response.last_name,
          response.email,
        ],
      };
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

    if (!result)
      throw new DoesNotExistsError(`User with email: ${email} does not exist`);

    return result.rows[0];
  }

  async getUserById(id) {
    const result = await pool.query(
      'SELECT * FROM public.user WHERE email=$1',
      [id],
    );

    if (!result)
      throw new DoesNotExistsError(`User with id: ${id} does not exist`);

    return result.rows[0];
  }

  async getAll() {
    const result = await pool.query('SELECT * FROM public.user');

    return result.rows;
  }

  /**
   *
   * @param {number} role User's role
   * @returns All users with given role
   */
  async getUsersByRole(role) {
    const result = await pool.query(
      'SELECT * FROM public.user WHERE "role"=$1',
      [role],
    );

    if (!result)
      throw new DoesNotExistsError(`Users with role: ${role} do not exist`);

    return result.rows;
  }

  /**
   *
   * @param {string} status User's status (available, busy)
   * @returns All users with given status
   */
  async getUsersByStatus(status) {
    const result = await pool.query(
      'SELECT * FROM public.user WHERE "status"=$1',
      [status],
    );

    if (!result)
      throw new DoesNotExistsError(`Users with status: ${status} do not exist`);

    return result.rows;
  }

  /**
   *
   * @param {string} status User's status (available, busy)
   * @returns All users with given status
   */
   async getUsersByStatus(status) {
    const result = await pool.query(
      'SELECT * FROM public.user WHERE "status"=$1',
      [status],
    );

    if (!result)
      throw new DoesNotExistsError(`Users with status: ${status} do not exist`);

    return result.rows;
  }

  /**
   * 
   * @param {string} transportType User's transport type 
   * @returns 
   */
  async getUsersByTransportType(transportType) {
    const result = await pool.query(
      'SELECT * FROM public.user WHERE "status"=$1',
      [transportType],
    );

    if (!result)
      throw new DoesNotExistsError(`Users with status: ${status} do not exist`);

    return result.rows;
  }

  async isPasswordValid(user) {
    return bcrypt.compareSync(
      user.password,
      await this.getUserByEmail(user.email),
    );
  }
}

export default new UserService();
