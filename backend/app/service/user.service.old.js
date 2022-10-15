import userRepository from '../repository/user.repository.js';

class UserService {
  async create(user) {
    try {
      const result = await userRepository.create(user)[0];
      return { ...[result.firstName, result.lastName, result.email] };
    } catch (err) {
      throw err;
    }
  }
}

const userService = new UserService();
export default userService;
