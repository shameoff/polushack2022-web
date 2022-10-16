import pool from '../config/db.config.js';

class TransportService {
  async getAllTransports() {
    return new Promise(async (resolve, reject) => {
      const result = await pool.query('SELECT * FROM public.transport');
      resolve(result.rows);
    });
  }

  async getAvailableTransport() {
    return new Promise(async (resolve, reject) => {
      const result = await pool.query(
        'SELECT * FROM public.transport WHERE "status"="available"',
      );
      resolve(result.rows);
    });
  }

  async getOccupiedTransport() {
    return new Promise(async (resolve, reject) => {
      const result = await pool.query(
        'SELECT * FROM public.transport WHERE "status"="occupied"',
      );
      resolve(result.rows);
    });
  }

  async getPendingTransport() {
    return new Promise(async (resolve, reject) => {
      const result = await pool.query(
        'SELECT * FROM public.transport WHERE "status"="pending"',
      );
      resolve(result.rows);
    });
  }

  async addTransport(
    type,
    characteristics = null,
    name,
    car_number,
    status = null,
  ) {
    return new Promise(async (resolve, reject) => {
      const result = await pool.query(
        `INSERT INTO public.transport("type", "characteristics", "name", "car_number", status) \
        VALUES (${type}, ${characteristics}, ${name}, ${car_number}, ${status}`,
      );
      resolve(result.status);
    });
  }

  async editTransport(
    type,
    characteristics = null,
    name,
    car_number,
    status = null,
  ) {
    return new Promise(async (resolve, reject) => {
      const result = await pool.query(
        `UPDATE public.transport SET \
        "type" = ${type},\
        "characteristics" = ${characteristics},
        "name" = ${name}\
        "status" = ${status}
        WHERE "car_number" = ${car_number}`,
      );
      resolve(result.status);
    });
  }

  async deleteTransport(car_number) {
    return new Promise(async (resolve, reject) => {
      const result = await pool.query(
        `DELETE FROM public.transport WHERE "car_number"=${car_number}`,
      );
      resolve(result.status);
    });
  }
}

export default new TransportService();
