import { Pool } from 'pg';

const pool = new Pool({
  user: 'someUser',
  host: 'localhost',
  database: 'someDatabase',
  password: 'somePassword',
  port: 5432,
});

export default pool;
