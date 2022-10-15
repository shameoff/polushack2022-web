import { check } from 'express-validator';
import app from '../app.js';
import authController from '../controller/auth.controller.js';
import authMiddleware from '../middlewares/auth.middleware.js';

app.use(authMiddleware);
app.post(
  '/user/register',
  [
    check('email', "Email can't be empty").notEmpty(),
    check(
      'password',
      'The password must be between 8 and 70 characters long',
    ).isLength({ min: 6, max: 70 }),
  ],
  authController.register,
);
app.post('/user/login', authController.login);

export default app;
