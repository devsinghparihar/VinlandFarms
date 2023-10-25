// config.js

const generateConfig = (token) => {
    const config = {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
    };
    return config;
  };
  
  export default generateConfig;
  