import * as dotenv from 'dotenv';
import app from './app/app.js';

dotenv.config({ path: '../.env' });

const SERVER_PORT = process.env.BACKEND_SERVER_PORT;

app.listen(SERVER_PORT);
