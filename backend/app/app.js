import express from 'express';
import router from './routes/index.js';
import cors from 'cors';

const app = express();
app.use(cors({ origin: '*' }));
app.use(express.json());
app.use('/', router);

export default app;
