import { Router } from 'express';
import TransportController from '../controller/transport.controller.js';

const transportRouter = Router();
transportRouter.get(
    '/transport/', (req, res) => {
                TransportController.getAllTransports(req, res);
              }
)

// transportRouter.get('/transport/', TransportController.getAllTransports)

export default transportRouter;
