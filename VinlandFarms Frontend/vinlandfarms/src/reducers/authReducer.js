// authReducer.js
const initialState = {
    isAuthenticated: false,
    username: '',
    role: '',
    token: '',
    id:''
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
          id:action.payload.id,
        };
      case 'LOGOUT':
        return initialState;
      default:
        return state;
    }
  };
  
  export default authReducer;
  