import HttpStatus from '../utils/HttpStatus.js';
import transportService from '../service/transport.service.js';

class TransportController {
    async getAllTransports(req, res){
        console.log("ПИНАЕМ ХУЙ!");
      transportService.getAllTransports().then((data)=>{
        console.log(data);
      });
      console.log(result);
      res.json(result);
    }
};

export default new TransportController();