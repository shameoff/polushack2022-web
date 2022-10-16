import bcrypt from 'bcrypt';
import pool from '../config/db.config.js';
import AlreadyExistsError from '../error/AlreadyExistsError.js';
import DoesNotExistsError from '../error/DoesNotExistError.js';
import UnexpectedError from '../error/UnexpectedError.js';
import UserStatus from '../utils/UserStatus.js';

function getHMSTime(date) {
  return date.toTimeString().split(' ')[0];
}

class UserService {
  _tableName = 'public."user"';

  async isUserExists(email) {
    const result = await pool.query(
      `SELECT EXISTS(SELECT * FROM ${this._tableName} WHERE email=$1)`,
      [email],
    );
    console.log(result);
    const exists = result.rows[0].exists;

    return exists === 't' || exists === 1 ? true : false;
  }

  async create(user) {
    if (await this.isUserExists(user.email))
      throw new AlreadyExistsError(
        `User with email ${user.email} already exists`,
      );

    const hash = bcrypt.hashSync(user.password, 10);

    try {
      const work_start_time = new Date();
      work_start_time.setHours(8, 0, 0);
      const work_end_time = new Date();
      work_end_time.setHours(17, 0, 0);

      const rest_start_time = new Date();
      rest_start_time.setHours(12, 0, 0);
      const rest_end_time = new Date();
      rest_end_time.setHours(13, 0, 0);

      const now = new Date();
      const status =
        work_start_time < now && now < work_end_time
          ? UserStatus[0]
          : UserStatus[3];

      const result = await pool.query(
        `INSERT INTO ${this._tableName} 
        (first_name, last_name, email, password_hash, role, status, work_start_time, work_end_time, rest_start_time, rest_end_time)
        VALUES($1, $2, $3, $4, $5, $6, $7, $8, $9, $10)`,
        [
          user.first_name,
          user.last_name,
          user.email,
          hash,
          user.role,
          status,
          getHMSTime(work_start_time),
          getHMSTime(work_end_time),
          getHMSTime(rest_start_time),
          getHMSTime(rest_end_time),
        ],
        /* [...Object.values({ user, password: hash })], */
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
        `Something went wrong while trying to create user. ERR: ${err}`,
      );
    }
  }

  async getUserByEmail(email) {
    const result = await pool.query(
      `SELECT * FROM ${this._tableName} WHERE email=$1`,
      [email],
    );

    if (!result)
      throw new DoesNotExistsError(`User with email: ${email} does not exist`);

    return result.rows[0];
  }

  async getUserById(id) {
    const result = await pool.query(
      `SELECT * FROM ${this._tableName} WHERE email=$1`,
      [id],
    );

    if (!result)
      throw new DoesNotExistsError(`User with id: ${id} does not exist`);

    return result.rows[0];
  }

  async getAll() {
    const result = await pool.query(`SELECT * FROM ${this._tableName}`);

    return result.rows;
  }

  /**
   *
   * @param {number} role User's role
   * @returns All users with given role
   */
  async getUsersByRole(role) {
    const result = await pool.query(
      `SELECT * FROM ${this._tableName} WHERE "role"=$1`,
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
      `SELECT * FROM ${this._tableName} WHERE "status"=$1`,
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
      `SELECT * FROM ${this._tableName} WHERE "status"=$1`,
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
      `SELECT * FROM ${this._tableName} WHERE "status"=$1`,
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
