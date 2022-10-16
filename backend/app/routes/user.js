import { Router } from 'express';
import userController from '../controller/user.controller.js';
import isObjectEmpty from '../utils/isObjectEmpty.js';

const userRouter = Router();

userRouter.get('/user', (req, res) => {
  if (isObjectEmpty(req.query)) userController.getAll(req, res);
  else if (req.query.role) userController.getUsersByRole(req, res);
  else if (req.query.status && req.query.transport_type && req.query.latitude && req.query.longitude) userController.getUserByStatus(req, res);
});
userRouter.get('/user/:id', userController.getUserById);

export default userRouter;
