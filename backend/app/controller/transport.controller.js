import HttpStatus from '../utils/HttpStatus.js';
import transportService from '../service/transport.service.js';
import isObjectEmpty from '../utils/isObjectEmpty.js';

class TransportController {
    async getTransport(req, res){
      if (isObjectEmpty(req.query))
      {
        transportService.getAllTransports()
        .then((data)=>{
          res.json(data);
        })
      } else {
        if (req.query.status === "available") res.json(await transportService.getAvailableTransport());
        else if (req.query.status === "occupied") res.json(await transportService.getOccupiedTransport());
        else if (req.query.status === "pending") res.json(await transportService.getPendingTransport());
        // else 
      }
      
    }
    async addTransport(req, res){
      type = req.data.type
      characteristics = req.data.characteristics
      name = req.data.name
      car_number = req.data.car_number
      status = req.data.car_number
      res.json(transportService.addTransport(type, characteristics, name, car_number, status))
    }
    async editTransport(req,res){
      type = req.data.type
      characteristics = req.data.characteristics
      name = req.data.name
      car_number = req.data.car_number
      status = req.data.car_number
      res.json(transportService.editTransport(type, characteristics, name, car_number, status))
    }
    async deleteTransport(req, res){
      car_number = req.data.car_number;
      res.json(transportService.deleteTransport(car_number));
      
    }
};

export default new TransportController();