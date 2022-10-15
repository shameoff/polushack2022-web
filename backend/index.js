import * as dotenv from 'dotenv';
import express from 'express';
import { check } from 'express-validator';
import authController from './app/controller/auth.controller.js';
import authMiddleware from './app/middlewares/auth.middleware.js';

dotenv.config({ path: '../.env' });

const SERVER_PORT = process.env.BACKEND_SERVER_PORT;
const app = express();
app.use(express.json());
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


app.listen(SERVER_PORT);

/* app.post('/user/register', (req, res) => {
  // const { firstName, lastName, email, password } = req.body;
  const tableName = 'user';

  pool.query(
    `SELECT EXISTS(SELECT * FROM public.${tableName} WHERE email=$1)`,
    [req.body.email],
    (err, result) => {
      if (err) {
        console.error(err);
      }

      if (result)
        res
          .status(409)
          .send(`User with email ${req.body.email} already exists`);
    },
  );

  bcrypt
    .hash(req.body.password, 10)
    .then((hash) => {
      pool.query(
        `INSERT INTO ${tableName} VALUES($1, $2, $3, $4)`,
        [Object.values(req.body)],
        (err, result) => {
          if (err) {
            console.error(err);
          }

          res.status(201).send(`User added with id: ${result.rows[0].id}`);
        },
      );
    })
    .catch((err) => {
      if (err) {
        res.status(400).json({ error: err });
      }
    });
}); */
