import axios from 'axios';
const apiUrl = process.env.REACT_APP_API_URL;

const registerUser = async (formData,emailCode) => {
    console.log("data", formData);

    const response = await axios.post(`${apiUrl}/register?codeGmailVerifyRegister=${emailCode}`, formData, {
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return response;
};
const sendMailCode = async (email) => {
    
    const url = `${apiUrl}/send-mail-code?email=${encodeURIComponent(email)}`;
    
    const response = await axios.post(url, null, {
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return response;
};


// Export registerUser as default
export default registerUser;

// Export sendMail as named export
export { sendMailCode };
