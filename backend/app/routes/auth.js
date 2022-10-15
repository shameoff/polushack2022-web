import { Router } from 'express';
import { check } from 'express-validator';
import authController from '../controller/auth.controller.js';
import authMiddleware from '../middlewares/auth.middleware.js';

const authRouter = Router();
authRouter.use(authMiddleware);
authRouter.post(
  '/user/register',
  [
    check('email', "Email can't be empty").notEmpty(),
    check(
      'password',
      'The password must be between 8 and 70 characters long',
    ).isLength({ min: 6, max: 70 }),
  ],
  (req, res) => {
    if (req.query) authController.registerByRole(req, res);
    else authController.register(req, res);
  },
);
authRouter.post('/user/login', authController.login);

export default authRouter;
