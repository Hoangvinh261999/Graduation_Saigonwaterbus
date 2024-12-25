import axios from "axios";
import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { formatCurrencyVND } from "../../utils/formatVnd";
import { formatDate } from "../../utils/formatDate";

const apiUrl = process.env.REACT_APP_API_URL;
function Dopayment() {
localStorage.getItem('idHd')
  let paymentWindow = null;
  const { t } = useTranslation();
  const [submitted, setSubmitted] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const chuyenMail = JSON.parse(localStorage.getItem('orderData'));
  const chuyenData = JSON.parse(localStorage.getItem('chuyenData'));
  const seatData = JSON.parse(localStorage.getItem('seatData'));
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post(`${apiUrl}/payment/vnpay`, {
        orderId: new Date().getTime().toString(), 
        amount: localStorage.getItem('total'),
                returnUrl: 'https://saigonwaterbus.click/api/saigonwaterbus/payment/vnpay/return'

      });
      paymentWindow = window.open(response.data, 'Payment', 'width=600,height=600');
      setSubmitted(true);
    } catch (error) {
      console.error('Error sending payment request:', error);
    }
  };



  useEffect(() => {
    const handlePaymentMessage = (event) => {
      if (event.data === 'payment_success') {
        closePaymentPopup();
        sendEmail();
    
          }
    };

    window.addEventListener('message', handlePaymentMessage);
    return () => {
      window.removeEventListener('message', handlePaymentMessage);
    };
  }, []);



const handleUpdateBookingComplete = async (email) => {
    try {
      const response = await axios.put(`${apiUrl}/save-booking-complete/${email}`);
      console.log('Response:', response.data);
    } catch (error) {
      console.error('Error sending payment request:', error);
    }

};

  const sendEmail = async () => {
               
    setIsLoading(true); // Show loading indicator

    if (!seatData) {
      setIsLoading(false);
      return;
    }

    const seatNames = seatData.map(seat => seat.seatName).join(', ');
    const to = chuyenMail.email;
    const subject = "Thanh toán thành công đặt vé Saigonwaterbus";
    const body = `
<div style="background-color: #f59e0b; padding: 16px;">
  <div style="max-width: 36rem; margin: 0 auto; font-family: sans-serif;">
    <div style="background-color: #ffffff;">
      <div style="text-align: center; padding-top: 10px;">
        <img
          src="https://ci3.googleusercontent.com/meips/ADKq_NbQGGbb_3r2Agkoi_wolOmotd_dfaLvqa3rxGadLs5FEBAE6EQemDdqfWWEKeSykTYdjt7IDrONZKZB_ts0nBNpjaeRfauBaDGM1o8-fytHkfY=s0-d-e1-ft#https://static.vexere.com/webnx/prod/img/saigon-waterbus.png"
          alt=""
          style="max-height: 6rem; object-fit: contain; margin: 0 auto;"
        />
      </div>
      <div>
        <img
          src="https://ci3.googleusercontent.com/meips/ADKq_NZa5q1i1-0E5fQ7_WCN6ThOUM29iy8CKshCv76tUYX5iWqWmv4I00_0Bo9DHmEdsf6qNeBlXvT3asoRs96aOX8vZw=s0-d-e1-ft#https://static.vexere.com/images/divider.png"
          alt="divider"
          style="width: 100%; max-height: 2.5rem;"
        />
      </div>
      <div style="padding: 0 24px 10px;">
        <div style="color: #854d0e; background-color: #fef3c7; padding: 16px; border: 1px solid #fcd34d; border-radius: 0.25rem; text-align: center; font-weight: bold; font-size: 0.875rem;">
          <p style="margin-bottom: 4px;">
            Quý khách vui lòng không phản hồi về địa chỉ email này. Quý khách vui lòng liên hệ số hotline 0393211895. Saigon Waterbus tiếp nhận và xử lí các yêu cầu từ cuộc gọi của quý khách trong giờ làm việc.
          </p>
          <p>Xin cảm ơn quý khách!</p>
        </div>
        <div style="font-weight: bold; font-size: 1.125rem; color: #1e40af; text-align: center; padding: 10px 0;">
          Cảm ơn quý khách ${chuyenMail.name} !
        </div>
        <p style="font-size: 0.75rem; margin: 4px 0;">
          Cảm ơn quý khách đã tin tưởng sử dụng dịch vụ của
          <a
            href="https://saigonwaterbus.click"
            target="_blank"
            style="color: #2563eb; text-decoration: underline;"
            rel="noreferrer"
          >
            Saigon Waterbus
          </a>.
        </p>
      </div>
      <div>
        <img
          src="https://ci3.googleusercontent.com/meips/ADKq_NZa5q1i1-0E5fQ7_WCN6ThOUM29iy8CKshCv76tUYX5iWqWmv4I00_0Bo9DHmEdsf6qNeBlXvT3asoRs96aOX8vZw=s0-d-e1-ft#https://static.vexere.com/images/divider.png"
          alt="divider"
          style="width: 100%; max-height: 2.5rem;"
        />
      </div>
      <div style="padding: 0 20px; font-size: 0.875rem; line-height: 1.5; color: #000000;">
        <div style="font-weight: bold; font-size: 1.125rem; color: #1e40af; text-align: center; padding: 10px 0;">
          Hướng dẫn các bước nhận vé và lên tàu
        </div>
        <ul style="padding-left: 28px; list-style-type: disc;">
          <li>
            Quý khách vui lòng đến điểm đón <b>trước ít nhất 10 PHÚT</b> (đối với ngày thường) hoặc <b>30-45 PHÚT</b> (đối với ngày Lễ Tết) <b>để in vé giấy/đổi vé giấy (nếu có) và làm thủ tục lên tàu</b>.
          </li>
          <li>
            Quý khách vui lòng <b>cung cấp số điện thoại chính xác</b> và <b>giữ điện thoại luôn mở</b> để nhân viên có thể liên lạc được.
          </li>
        </ul>
      </div>
      <div>
        <img
          src="https://ci3.googleusercontent.com/meips/ADKq_NZa5q1i1-0E5fQ7_WCN6ThOUM29iy8CKshCv76tUYX5iWqWmv4I00_0Bo9DHmEdsf6qNeBlXvT3asoRs96aOX8vZw=s0-d-e1-ft#https://static.vexere.com/images/divider.png"
          alt="divider"
          style="width: 100%; max-height: 2.5rem;"
        />
      </div>
      <div style="padding: 0 24px 10px; font-family: sans-serif; font-size: 0.875rem; line-height: 1.75;">
        <div style="border: 1px solid #059669; border-radius: 0.5rem; width: 100%;">
          <div style="background-color: #059669; border-radius: 0.5rem 0.5rem 0 0; width: 100%;">
            <table style="width: 100%;">
              <tbody>
                <tr>
                  <td style="width: 100%;">
                    <div style="float: left; padding: 6px;">
                      <img
                        src="https://ci3.googleusercontent.com/meips/ADKq_NbQGGbb_3r2Agkoi_wolOmotd_dfaLvqa3rxGadLs5FEBAE6EQemDdqfWWEKeSykTYdjt7IDrONZKZB_ts0nBNpjaeRfauBaDGM1o8-fytHkfY=s0-d-e1-ft#https://static.vexere.com/webnx/prod/img/saigon-waterbus.png"
                        alt=""
                        style="height: 2.5rem;"
                      />
                    </div>
                    <div style="float: right; width: 15rem; color: #ffffff; text-align: right; font-weight: bold; font-size: 1.125rem; padding: 10px;">
                      <span>Mã vé:</span> <span style="color: #f59e0b;">${localStorage.getItem('idHd')}</span>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
<div style="background-color: #ffffff; padding-bottom: 16px; position: relative;">
    <div style="padding: 0 14px;">
        <div style="width: 100%; padding: 10px 5px; text-align: center; position: relative; z-index: 1;">
            <img 
                src="https://ci3.googleusercontent.com/meips/ADKq_NZg4kMOo6qYPjqINxie_RCZD_S_b5h0WTD1P5VSJovy40m_U4WIj_FpgWSh9BEZMSQlAslzAL2h9upZw-0DHLHWbhK-Ag=s0-d-e1-ft#https://static.vexere.com/images/paid-stamp.png" 
                alt="đã thanh toán" 
                width="184px" 
                style="position: absolute; top: 10px; left: 50%; transform: translateX(-50%); opacity: 0.3; z-index: -1;" 
            />
        </div>
        <h3 style="font-size: 18px; color: #1e40af; text-align: center; margin: 1px 0;">
            Thông tin đơn hàng
        </h3>
        <table style="width: 100%; vertical-align: top; font-size: 0.75rem; color: #000000; line-height: 1.5; font-family: sans-serif;">
            <tbody>
                <tr>
                    <td style="font-size: 14px;">
                        <strong>Bến khởi hành:</strong> ${chuyenData.startTerminal}
                    </td>
                </tr>
                <tr>
                    <td style="font-size: 14px;">
                        <strong>Bến kết thúc:</strong> ${chuyenData.endTerminal}
                    </td>
                </tr>
                <tr>
                    <td style="font-size: 14px;">
                        <strong>Thời gian khởi hành:</strong> ${chuyenData.departureTime}
                    </td>
                </tr>
                <tr>
                    <td style="font-size: 14px;">
                        <strong>Số ghế đã đặt:</strong> ${seatNames}
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

        </div>
      </div>
    </div>
  </div>
<p style="font-size: 16px; color: #FFFFFF; text-align: center; font-weight: bold;">
  Một lần nữa xin cảm ơn bạn đã sử dụng dịch vụ của chúng tôi. Vui lòng giữ mã QR đính kèm trong email khi tới bến.
</p>
</div>
    `;
 
    const emailContent = `Ngày khởi hành: ${chuyenData.departureDate} \nBến khởi hành: ${chuyenData.startTerminal} \nBến kết thúc: ${chuyenData.endTerminal} \nThời gian khởi hành: ${chuyenData.departureTime} \nSố ghế: ${seatNames}`;
    const emailData = {
      to: to,
      subject: subject,
      body: body,
      contentForQR: emailContent
    };

    try {
          handleUpdateBookingComplete(to);

      const response = await axios.post(`${apiUrl}/send-mail`, emailData);
      const redirectUrl = `/dat-ve/thanh-toan-thanh-cong?email=${encodeURIComponent(chuyenMail.email)}&idHD=${encodeURIComponent(localStorage.getItem('idHd'))}`;
      window.location.href = redirectUrl;

    } catch (error) {
      console.error('Error calling the send-mail API:', error);
    } finally {

      setIsLoading(false);
    }

  };

  const closePaymentPopup = () => {
    if (paymentWindow) {
      paymentWindow.close();
    }
  };
    const [timeLeft, setTimeLeft] = useState(0);

useEffect(() => {
    const expirationTime = localStorage.getItem('expirationTime');

    if (expirationTime) {
        let intervalId; // Khai báo biến intervalId trước khi sử dụng nó trong calculateTimeLeft

        const calculateTimeLeft = () => {
            const now = new Date().getTime();
            const endTime = new Date(expirationTime).getTime();
            const distance = endTime - now;

            if (distance <= 0) {
                setTimeLeft(0);
                clearInterval(intervalId);
                localStorage.clear();
            } else {
                setTimeLeft(Math.floor(distance / 1000));
            }
        };

        calculateTimeLeft(); 
        intervalId = setInterval(() => {
            calculateTimeLeft();
        }, 1000); // Cập nhật mỗi giây

        return () => clearInterval(intervalId);
    }
}, []);

const seatNames = seatData ? seatData.map(seat => seat.seatName).join(', ') : ''; 

    const formatTime = (seconds) => {
        const minutes = Math.floor(seconds / 60);
        const remainingSeconds = seconds % 60;
        return `${String(minutes).padStart(2, '0')}:${String(remainingSeconds).padStart(2, '0')}`;
    };
    return (    
 
<>
                   <div className="flex items-center justify-center bg-stone-200 h-64">
                    <div className="container mx-auto">
                        <h1 className="qodef-m-title entry-title text-sm lg:text-5xl text-center font-bold ">
                            {t("doPayment.ticketPayment")}
                        </h1>
                    </div>
                </div>
<div className="container mx-auto bg-white p-6">
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
        <div className="bg-gray-100 p-4 rounded-lg shadow-md max-w-sm mx-auto">
            <div className="text-center mb-4 text-lg font-semibold text-gray-800">
                {t("doPayment.remainingTime")}
            </div>
            <div className="flex items-center justify-center space-x-4">
                <div className="flex items-center justify-center">
                    <img
                        src="https://static.vexere.com/webnx/prod/widget/images/timer.svg"
                        alt="Timer"
                        className="w-8 h-8"
                    />
                </div>
                <div className="text-3xl font-bold text-red-600">
                    {formatTime(timeLeft)}
                </div>
            </div>
        </div>

   <div className="flex">
   <div className="w-full container mx-auto">
   
  <div className="p-4 space-y-4">

  <div className="space-y-4 p-4 sm:p-6 md:p-8">
    <div className="justify-end flex ">
    <h2 className="text-lg font-semibold mx-2">{t("doPayment.totalAmount")}</h2>
    <div className="text-xl font-bold  text-blue-500">{formatCurrencyVND(localStorage.getItem('total'))}</div>
  </div>
  <h2 className="text-lg sm:text-xl md:text-2xl font-semibold">{t("doPayment.tripInfo")}</h2>
  <div className="border border-gray-300 rounded p-4 sm:p-6 md:p-8 space-y-2">
    <div>
      <div className="font-semibold text-base sm:text-lg text-center">{t("doPayment.info")}</div>
      <div className="text-gray-700 text-sm sm:text-base">
        <span className="font-medium text-black">{t("departureDate")} {formatDate(chuyenData.departureDate)}</span>
        <br />
          {t("doPayment.totalSeat")}
      </div>
      <hr className="my-2" />
      <div className="space-y-2">
        <div className="flex justify-between">
          <div className="font-semibold text-sm sm:text-base">{t("doPayment.customerInfo")}</div>
          {/* <div className="font-semibold">1 người</div> */}
        </div>
        <div className="border border-gray-200 rounded p-2 sm:p-4 space-y-2">
          <div className="flex justify-between items-center">
            <div className="flex items-center space-x-2">
              <div className="text-sm sm:text-base">{chuyenMail.phone}</div>
              <img
                alt=""
                src="https://static.vexere.com/webnx/prod/widget/images/phone-icon.svg?v=1"
                className="w-4 h-4"
              />
            </div>
          </div>
          <div className="space-y-2">
            <div className="flex items-center space-x-2">
              <img
                alt=""
                src="https://static.vexere.com/webnx/prod/widget/images/from-icon.svg"
              />
              <span className="text-sm sm:text-base">{chuyenData.departureTime}</span>
              <div className="text-gray-700 text-sm sm:text-base">{t("doPayment.wharf")} {chuyenData.startTerminal}</div>
            </div>
            <div className="flex items-center space-x-2">
              <img
                alt=""
                src="https://static.vexere.com/webnx/prod/widget/images/to-icon.svg"
              />
              <span className="text-sm sm:text-base">{chuyenData.arrivalTime}</span>
              <div className="text-gray-700 text-sm sm:text-base">{t("doPayment.wharf")} {chuyenData.toTerminal}</div>
            </div>
          </div>
          <div>
            <div className="font-semibold text-sm sm:text-base">{t("doPayment.idNumber")} : {seatNames}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <h2 className="text-lg sm:text-xl md:text-2xl font-semibold">{t("doPayment.contactInfo")}</h2>
  <div className="border border-gray-300 rounded p-4 sm:p-6 md:p-8 space-y-2">

    <div className="flex justify-between">
      <div className="font-medium text-sm sm:text-base">{t("doPayment.idTicket")}</div>
      <div className="font-bold text-sm sm:text-base">{localStorage.getItem('idHd')}</div>
    </div>
    <hr className="my-2" />
    <div className="flex justify-between">
      <div className="font-medium text-sm sm:text-base">{t("doPayment.name")}</div>
      <div className="text-sm sm:text-base">{chuyenMail.name}</div>
    </div>
    <hr className="my-2" />
    <div className="flex justify-between">
      <div className="font-medium text-sm sm:text-base">{t("doPayment.contact")}</div>
      <div className="text-sm sm:text-base">{chuyenMail.phone}</div>
    </div>
    <hr className="my-2" />
<div className="flex justify-between">
  <div className="font-medium text-sm sm:text-base">{t("doPayment.email")}</div>
  <div className="text-sm sm:text-base max-w-full truncate text-right">
    {chuyenMail.email}
  </div>
</div>


    <hr className="my-2" />
    <div className="flex justify-between">
      <div className="font-medium text-sm sm:text-base">{t("doPayment.meno")}</div>
      <div className="text-sm sm:text-base">{chuyenMail.message}</div>
    </div>
  </div>
</div>

<div className="flex flex-col items-center space-y-4">
  <img
    className="payment-method-detail-img w-32 h-auto"
    src="https://static.vexere.com/webnx/prod/img/vn_pay.svg"
    alt="VNPay"
  />
<form onSubmit={handleSubmit}>
      <button className="bg-blue-600 text-white font-semibold py-2 px-6 rounded-lg shadow-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50 transition duration-300">
          {t("doPayment.makePayment")}
  </button>
</form>

</div>


</div>

        </div>
        </div>

  </div>
</>


     );
}

export default Dopayment;