import { Router } from "express";
import authRouter from "./auth.js";
import userRouter from "./user.js";
import transportRouter from "./transport.js"
const router = Router();

router.use(authRouter);
router.use(userRouter);
router.use(transportRouter);
export default router;