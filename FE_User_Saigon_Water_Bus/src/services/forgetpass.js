// src/services/apiService.js
import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_URL;

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});
const sendMailCode = async (email) => {    
    const url = `/send-mailcode-forgetpass?email=${encodeURIComponent(email)}`;
    
    const response = await apiClient.get(url,{
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return response;
};


const resetPassword = async (inputCode, email, confirmPassword) => {
    const url = `/forgot-password?gmailForget=${encodeURIComponent(email)}&codeGmail=${encodeURIComponent(inputCode)}&newPass=${confirmPassword}`;

  try {
    const response = await apiClient.post(url)
    return response;
  } catch (error) {
    // console.error('Error searching trips:', error);
    throw error;
  }
};

const forgetService = {
  sendMailCode,
  resetPassword
  // Có thể thêm các hàm khác ở đây
};

export default forgetService;
