import React, { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import { formatCurrencyVND } from '../../utils/formatVnd';
import axios from 'axios';
import { formatDate } from '../../utils/formatDate';

const apiUrl = process.env.REACT_APP_API_URL;

function ThanhToanThanhCong() {
    const { t } = useTranslation();
    const [thongTinVe, setThongTinVe] = useState(null); 
    const [loading, setLoading] = useState(true); 

    const checkticket = async (email, idHD) => {
       localStorage.setItem('chuyenData', JSON.stringify([]));
      localStorage.setItem('seatData', JSON.stringify([])); 
      localStorage.setItem('total', '0');
      localStorage.setItem('orderData', JSON.stringify({})); 
      localStorage.setItem('paymentStatus', '');
      localStorage.setItem('expirationTime', '');
      localStorage.setItem('idHd', '');
        try {
            const response = await axios.get(`${apiUrl}/checking?email=${email}&invoiceid=${idHD}`);
            if (response.data.result && Array.isArray(response.data.result)) {
                setThongTinVe(response.data.result[0]); 
            } else {
                setThongTinVe(null); 
            }
            setLoading(false); 
        } catch (error) {
            console.error('Error occurred while fetching ticket information:', error);
            setThongTinVe(null); 
            setLoading(false); 
        }
    };

    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        const emailParam = params.get('email');
        const idHDParam = params.get('idHD'); 
        
        if (emailParam && idHDParam) {
            checkticket(emailParam, idHDParam);
        } else {
            setLoading(false); 
        }
    }, []); 

    if (loading) {
        return <div className="p-4 bg-white container mx-auto">Đang tải thông tin vé...</div>;
    }

    if (!thongTinVe) {
        return <div className="p-4 bg-white container mx-auto">Không tìm thấy thông tin vé.</div>;
    }

    return (
        <>
            <div className="flex items-center justify-center bg-stone-200 h-64">
                <div className="container mx-auto">
                    <h1 className="qodef-m-title entry-title text-sm lg:text-5xl text-center font-bold ">
                       {t("paymentSuccess")}
                    </h1>
                </div>
            </div>

            <div className="p-4 bg-white container mx-auto">
                <div className="flex flex-col lg:flex-row">
                    {/* Left Container */}
                    <div className="flex-1 p-4 bg-gray-100 rounded-md shadow-lg lg:w-8/12">
                        <div className="text-center font-bold text-xl mb-6">{t("infoTicket")}</div>
                        <div className="text-green-500 text-2xl flex items-center mb-4">
                            <svg
                                className="w-6 h-6 mr-2"
                                viewBox="64 64 896 896"
                                fill="currentColor"
                            >
                                <path d="M699 353h-46.9c-10.2 0-19.9 4.9-25.9 13.3L469 584.3l-71.2-98.8c-6-8.3-15.6-13.3-25.9-13.3H325c-6.5 0-10.3 7.4-6.5 12.7l124.6 172.8a31.8 31.8 0 0051.7 0l210.6-292c3.9-5.3.1-12.7-6.4-12.7z" />
                                <path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z" />
                            </svg>
                       {t("paymentSuccess")}
                        </div>
                        <div className="bg-white p-4 rounded-md shadow-md">
                            <div className="mb-4">
                                <div>
                                     {t("checkTicket.sendMail")}:
                                    <span className="font-bold">{thongTinVe.emailBooking}</span>
                                </div>
                                <div>
                                                             {t("checkTicket.note")}:
                                    <span className="font-bold">0393211895</span>
                                </div>
                            </div>
                            <hr className="my-4" />
                            <div>
                                <div className="font-semibold mb-2">{t("checkTicket.instructions")}</div>
                                <div className="mb-4">
                                                            {t("checkTicket.detail")}

                                </div>
                            </div>
                            <hr className="my-4" />
                            <div>
                                <div className="font-semibold mb-2">{t("checkTicket.pickUp")}</div>
                                <div className="mb-2">{t("checkTicket.wharf")}  {thongTinVe.fromStationName}</div>
                                <div>
                                                                {t("checkTicket.pickUpTime")}:

                                    <span className="font-bold">{thongTinVe.tripStartTime} {formatDate(thongTinVe.departureDate)}</span>
                                </div>
                            </div>
                            <hr className="my-4" />
                            <div>
                                <div className="font-semibold mb-2">{t("checkTicket.return")}</div>
                                <div className="mb-2">{t("checkTicket.wharf")}  {thongTinVe.toStationName}</div>
                                <div>
                                    {t("checkTicket.returnTime")}:{" "}
                                    <span className="font-bold">{thongTinVe.tripEndtime}</span>
                                </div>
                            </div>
                            <hr className="my-4" />
                            <div>
                                <div className="font-semibold mb-2">{t("checkTicket.cancelTicket")}</div>
                                <div className="mb-2">
                                    {t("checkTicket.detailCancel")}
                                </div>
                            </div>
                        </div>
                    </div>
                    {/* Right Container */}
                    <div className="flex-1 p-4 bg-gray-100 rounded-md shadow-lg lg:ml-4 lg:w-4/12">
                        <div className="text-center font-bold text-xl mb-6">{t("checkTicket.ticketDetail")}</div>
                        <div className="bg-white p-4 rounded-md shadow-md">
                            <div className="mb-4">
                                <div>{t("checkTicket.idTicket")}:</div>
                                <div className="font-bold">{thongTinVe.id}</div>
                            </div>
                            <div className="mb-4">
                                <div>{t("checkTicket.seat")}:</div>
                                <div className="font-bold">{thongTinVe.seatsName}</div>
                            </div>
                            <div className="mb-4">
                                <div>{t("checkTicket.pickUp")}:</div>
                                <div className="font-bold">{thongTinVe.fromStationName}</div>
                            </div>
                            <div className="mb-4">
                                <div>{t("checkTicket.return")}:</div>
                                <div className="font-bold">{thongTinVe.toStationName}</div>
                            </div>
                            <div className="mb-4">
                                <div>{t("doPayment.departureDate")}:</div>
                                <div className="font-bold">
                                    {formatDate(thongTinVe.departureDate)}
                                </div>
                            </div>
                            <div className="mb-4">
                                <div>{t("checkTicket.pickUpTime")}:</div>
                                <div className="font-bold">
                                    {thongTinVe.tripStartTime}
                                </div>
                            </div>
                            <div className="mb-4">
                                <div>{t("checkTicket.ticketPrice")}:</div>
                                <div className="font-bold">
                                    {formatCurrencyVND(thongTinVe.totalAmount)}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default ThanhToanThanhCong;
