
import axios from 'axios';
const apiUrl = process.env.REACT_APP_API_URL;

const apiClient = axios.create({
  baseURL: apiUrl,
  headers: {
    'Content-Type': 'application/json',
  },
});

const LoginProcess = async (username, password) => {

    try {
        // Gửi yêu cầu POST với body là một đối tượng JSON chứa thông tin đăng nhập
        const response = await apiClient.post(`login`, { username, password});
    
        const responseDataUsername = response.data.result.username;
        localStorage.setItem('token', response.data.result.token);
        localStorage.setItem('us', username);
                // window.location.href = '/';

        return "oke";
    } catch (error) {
        console.error('Login failed:', error);
        throw error;
    }
};


const LoginGoogle= async (code) =>{
    try {
    const response = await apiClient.get(`/accesstoken?code=${code}`);
        localStorage.setItem('token', response.data.token);
         localStorage.setItem('us', response.data.username);
  console.log("data"+response.data)
            return response.data;
    } catch (error) {
      throw new Error('Error processing Google code: ' + error.message);
    }
};


const LoginService = {
  LoginProcess,LoginGoogle
};

export default LoginService;