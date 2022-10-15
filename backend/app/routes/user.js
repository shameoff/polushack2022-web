import { Router } from 'express';
import HttpStatus from '../utils/HttpStatus.js';

const userRouter = Router();

userRouter.get('/user/', (req, res) => {
  res.status(HttpStatus.OK).send('OKKKKKKKKKKK');
});

export default userRouter;
