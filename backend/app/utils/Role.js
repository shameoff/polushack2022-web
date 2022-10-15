const Role = Object.freeze({
  ADMIN: 1,
  DISPATCHER: 2,
  REQUESTER: 3,
  USER: 4,
  getRoleByName: (name) => {
    return Role[name.toLowerCase()];
  }
});

export default Role;
