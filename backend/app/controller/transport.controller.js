import HttpStatus from '../utils/HttpStatus.js';
import transportService from '../service/transport.service.js';

class TransportController {
    async getAllTransports(req, res){
      transportService.getAllTransports()
      .then((data)=>{
        res.json(data);
      });
      
    }
};

export default new TransportController();