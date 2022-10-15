import bcrypt from 'bcrypt';
import pool from '../config/db.config.js';
import AlreadyExistsError from '../error/AlreadyExistsError.js';
import DoesNotExistsError from '../error/DoesNotExistError.js';
import UnexpectedError from '../error/UnexpectedError.js';

class TransportService {
  async getAllTransports() {
    return new Promise(async (resolve,reject) =>{
      
      const result = await pool.query(
        'SELECT * FROM public.transport'
      ) 
      // console.log(result.rows);
      resolve(result.rows);
  })
  }
}

export default new TransportService();
