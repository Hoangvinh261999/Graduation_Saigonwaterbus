// src/services/apiService.js
import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_URL;

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

const timGhe = async (chuyenId) => {
  try {
    const response = await apiClient.get(`/booking-ticket/${chuyenId}`);
    return response.data.result;
  } catch (error) {
    console.error('Error fetching seat labels:', error);
    throw error;
  }
};

const timGheBooked = async (tripId,dateTrip) => {
  try {
    const response = await apiClient.get(`/booking-ticket/${tripId}/${dateTrip}/getSeat`);
    console.log(response.data.result)
    return response.data.result;
  } catch (error) {
    throw error;
  }
};
const timChuyen = async (searchParams) => {
  try {
    const response = await apiClient.get('/booking-ticket', {
      params: searchParams,
    });
        console.log(response.data.result)

    return response.data.result;
  } catch (error) {
    console.error('Error searching trips:', error);
    throw error;
  }
};

const apiService = {
  timGhe,
  timChuyen,timGheBooked
};

export default apiService;
