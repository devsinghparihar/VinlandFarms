// authReducer.js
const initialState = {
    isAuthenticated: false,
    username: '',
    role: '',
    token: '',
  };
  
  const authReducer = (state = initialState, action) => {
    switch (action.type) {
      case 'LOGIN_SUCCESS':
        return {
          ...state,
          isAuthenticated: true,
          username: action.payload.username,
          role: action.payload.role, // Store the role as a single variable
          token: action.payload.token,
        };
      case 'LOGOUT':
        return initialState;
      default:
        return state;
    }
  };
  
  export default authReducer;
  