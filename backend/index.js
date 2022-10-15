import * as dotenv from 'dotenv';
import app from './app/app.js';

dotenv.config({ path: '../.env' });

const SERVER_PORT = process.env.BACKEND_SERVER_PORT;

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
