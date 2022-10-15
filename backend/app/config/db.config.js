import * as dotenv from 'dotenv';
import pkg from 'pg';

dotenv.config({ path: '../.env' })

const { Pool } = pkg;
const pool = new Pool({
  user: process.env.POSTGRES_USER,
  host: process.env.POSTGRES_HOST,
  database: process.env.POSTGRES_DB,
  password: process.env.POSTGRES_PASSWORD,
});

export default pool;
