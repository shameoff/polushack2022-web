import app from '../app.js';
import TransportController from '../service/transport.service.js';


// app.get(
//     '/transport/', (req, res) => {
//         console.log("HEILLEW!");
//         TransportController.getAllTransports(req, res);
//       }
// )
app.get ('/transport/', TransportController.getAllTransports);


export default app;
