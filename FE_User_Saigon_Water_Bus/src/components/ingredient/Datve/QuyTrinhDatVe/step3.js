import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useTranslation } from 'react-i18next';
import PopupDone from '../../../../utils/popup/popupDone';
import usePopup from '../../../../utils/popup/usePopup';
const apiUrl = process.env.REACT_APP_API_URL;

const Step3 = ({ prevStep }) => {
 const { isOpen, message1, type, showPopup, closePopup } = usePopup();
  let paymentWindow = null;
  const { t } = useTranslation();
  const [submitted, setSubmitted] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [userDetails, setUserDetails] = useState({
    name: '',
    email: '',
    phone: '',
    message: '',
    trip: JSON.parse(localStorage.getItem('chuyenData')) || {},
    seat: JSON.parse(localStorage.getItem('seatData')) || [],
    total: localStorage.getItem('total')
  });
    const [userDetails1, setUserDetails1] = useState({
    name: '',
    email: '',
    phone: '',
    message: '',
    trip: JSON.parse(localStorage.getItem('chuyenData')) || {},
    seat: JSON.parse(localStorage.getItem('seatData')) || [],
    total: localStorage.getItem('total')
  });
  useEffect(() => {
      const isLoggedIn = localStorage.getItem("isLoggedIn")|| "";
      const token = localStorage.getItem("token");
    const fetchUserDetail = async () => {
      if (isLoggedIn !== "") {
        try {
          const headers = { Authorization: `Bearer ${token}` };
          const response = await axios.get(`${apiUrl}/profile`, { headers });
          const firstname = response.data.result.firstname || "";
          const lastname = response.data.result.lastname || "";
          const phoneNumber = response.data.result.phoneNumber;

          const updatedUserDetails = {
            name: firstname + (firstname && lastname ? " " : "") + lastname,
            email: response.data.result.email,
            phone: (phoneNumber && !phoneNumber.startsWith("Not")) ? phoneNumber : ""
          };

          setUserDetails(updatedUserDetails);
        } catch (error) {
          console.error("Error fetching user detail:", error);
        }
      }
    };


    if (isLoggedIn !== "") {
      fetchUserDetail();
    }
  }, []);



    const { name, email, message, phone, trip, seat, total } = userDetails;
    const orderData = { name, email, message, phone, trip, seat, total };
    localStorage.setItem('orderData', JSON.stringify(orderData)); // Lưu thông tin đơn hàng vào localStorage

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setUserDetails((prevUserDetails) => ({
      ...prevUserDetails,
      [name]: value
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const seatData = JSON.parse(localStorage.getItem('seatData')) || [];
    const chuyenData=JSON.parse(localStorage.getItem('chuyenData')) || [];
 try {
        const response = await axios.post(`${apiUrl}/check-ticket`, seatData, {
            params: {
                departureDate: chuyenData.departureDate
            }
        });

        if (response.data.code === 500) {
             showPopup('Danh sách ghế đã thay đổi ,vui lòng tìm và chọn lại ghế', 'fail');
            return;
        }else{
          const localStorageData = {
            total: localStorage.getItem('total'),
            chuyenData: localStorage.getItem('chuyenData'),
            seatData: localStorage.getItem('seatData'),
            orderData: localStorage.getItem('orderData'),
            us:localStorage.getItem("us")
          };
            const now = new Date();
            localStorage.setItem('paymentStatus', 'inProgress');
            const expirationTime = new Date(now.getTime() + 12 * 60 * 1000);
            const expirationTimeString = expirationTime.toISOString();
            localStorage.setItem('expirationTime', expirationTimeString);

            try {
            const response= await axios.post(`${apiUrl}/hold-ticket`, { localStorageData });
              localStorage.setItem('idHd',response.data)
            } catch (error) {
              console.error('Error sending localStorage data to server:', error);
            }
              window.location.href = '/dopayment';

        }

    } catch (error) {
        console.error('Error:', error);
    }
    try {
      const response = await axios.post(`${apiUrl}/payment/vnpay`, {
        orderId: new Date().getTime().toString(), // Sử dụng timestamp làm orderId
        amount: total,
        // returnUrl: `${apiUrl}/payment/vnpay/return`
                returnUrl: 'https://saigonwaterbus.click/api/saigonwaterbus/payment/vnpay/return'

      });
      paymentWindow = window.open(response.data, 'Payment', 'width=600,height=600');
      setSubmitted(true);
    } catch (error) {
      console.error('Error sending payment request:', error);
    }
  };

  function formatDate(dateString) {
    const [year, month, day] = dateString.split('-');
    return `${day}-${month}-${year}`;
  }

  return (
      <div className="container mx-auto max-w-md mt-10">
       <PopupDone isOpen={isOpen} message1={message1} type={type} onClose={closePopup} />

        {isLoading && (
            <div className="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-75 z-50">
              <div className="flex items-center space-x-2 text-white text-lg">
                <svg className="animate-spin h-6 w-6 text-white" viewBox="0 0 24 24">
                  <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor"
                          strokeWidth="4"></circle>
                  <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V2.5"></path>
                </svg>
                <span className='text-base'>{t('paymentProcessing')}</span>
              </div>
            </div>
        )}
        <h2 className="text-2xl font-bold text-center">{t('contactBooking')}</h2>
        <form className="mt-8 text-sm 2xl:text-base " onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block font-semibold  text-gray-700 mb-2">{t('name')} *</label>
            <input
                type="text"
                name="name"
                value={userDetails.name}
                onChange={handleInputChange}
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-blue-500 focus:ring-2"
                required
            />
          </div>

          <div className="mb-4">
            <label className="block font-semibold text-gray-700 mb-2">{t('phone')}</label>
            <input
                type="tel"
                name="phone"
                value={userDetails.phone}
                onChange={handleInputChange}
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-blue-500 focus:ring-2"
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 mb-2 font-semibold">{t('email')} *</label>
            <input
                type="email"
                name="email"
                value={userDetails.email}
                onChange={handleInputChange}
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-blue-500 focus:ring-2"
                required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 mb-2 font-semibold">{t('message')}</label>
            <textarea
                name="message"
                value={userDetails.message}
                onChange={handleInputChange}
                rows={4}
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-blue-500 focus:ring-2"
            />
          </div>

          <div className="mt-4 flex justify-between">
            <button
                className="button bg-green-500 hover:bg-green-700 text-white font-bold p-2 text-sm 2xl:text-base rounded flex items-center"
                onClick={prevStep}>
              <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="size-6 mr-2"
              >
                <path strokeLinecap="round" strokeLinejoin="round" d="M15.75 19.5 8.25 12l7.5-7.5" />
              </svg>
              {t('back')}
            </button>
            <button
                type="submit"
                className="px-4  text-sm 2xl:text-base font-bold  bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-blue-500 focus:ring-2"
            >
              {t('pay')}
            </button>
          </div>
        </form>
      </div>
  );
};

export default Step3;
