import { Router } from 'express';
import TransportController from '../controller/transport.controller.js';

const transportRouter = Router();
transportRouter.get(
  '/transport/', (req, res) => {
    TransportController.getTransport(req, res);
  }
)
transportRouter.post(
  '/transport/', (req, res) => {
    TransportController.addTransport(req,res);  
  }
)
transportRouter.put(
  '/transport/:id', (req, res) => {
    TransportController.editTransport(req,res);  
  }
)
transportRouter.delete(
  '/transport/:id', (req, res) => {
    TransportController.addTransport(req,res);  
  }
)
// transportRouter.get('/transport/', TransportController.getAllTransports)

export default transportRouter;
