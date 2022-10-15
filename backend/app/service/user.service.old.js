import AlreadyExistsError from '../error/AlreadyExistsError.js';
import userRepository from '../repository/user.repository.js';

class UserService {
  async create(user) {
    if (userRepository.isUserExists(user.email)) {
      throw new AlreadyExistsError(`User with email ${email} already exists`);
    }

    try {
      const result = await userRepository.create(user)[0];
      return { ...[result.first_name, result.last_name, result.email] };
    } catch (err) {
      throw err;
    }
  }
}

export default new UserService();;
