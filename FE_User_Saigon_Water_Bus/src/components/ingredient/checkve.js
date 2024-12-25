import axios from "axios";
import { useEffect, useState } from "react";
import { formatCurrencyVND } from "../../utils/formatVnd";
import { formatDate } from "../../utils/formatDate";
import {useTranslation} from "react-i18next";
import PopupDone from "../../utils/popup/popupDone";
import usePopup from "../../utils/popup/usePopup";
const apiUrl = process.env.REACT_APP_API_URL;
function CheckTicket() {
    const {t} = useTranslation();
    const [email, setEmail] = useState('');
    const [idHD, setIdHD] = useState('');
    const [closeCheck, setCloseCheck] = useState(true);
    const [thongTinVe, setThongTinVe] = useState([]);
      const { isOpen, message1, type, showPopup, closePopup } = usePopup();

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        if (name === 'email') {
            setEmail(value);
        } else if (name === 'idHD') {
            setIdHD(value);
        }
    };

    const checkticket = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.get(`${apiUrl}/checking?email=${email}&invoiceid=${idHD}`);
            if(response.data.code===1005){
            setThongTinVe(response.data.result); 
            setCloseCheck(false);
            }else{

             showPopup('Không tồn tại thông tin vé !', 'fail');
            }
        } catch (error) {
            console.error('Error occurred while fetching ticket information:', error);
        }
    };


    return ( 
        <>
     <PopupDone isOpen={isOpen} message1={message1} type={type} onClose={closePopup} />
                        <div className="flex items-center justify-center bg-stone-200 h-64">
                    <div className="container mx-auto">
                        <h1 className="qodef-m-title entry-title text-sm lg:text-5xl text-center font-bold ">
                            {t("checkTicket.checkTicket")}
                        </h1>
                    </div>
                </div>
            <div className="flex justify-center ">
            
                {closeCheck &&
                    <div className="w-full max-w-lg bg-white shadow-lg rounded-lg p-6 my-4">
                        <div className="text-center text-2xl font-semibold mb-6">
                            {t("checkTicket.checkInfo")}
                        </div>
                        <form>
                            <div className="space-y-4">
                                <div className="flex flex-col">
                                    <label className="text-sm font-medium mb-2">{t("checkTicket.idTicket")}</label>
                                    <input
                                        placeholder={t("checkTicket.inputIdTicket")}
                                        name="idHD"
                                        type="text"
                                        value={idHD}
                                        onChange={handleInputChange}
                                        className="p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-yellow-400"
                                    />
                                </div>
                                <div className="flex flex-col">
                                    <label className="text-sm font-medium mb-2">{t("checkTicket.email")}</label>
                                    <input
                                        placeholder={t("checkTicket.inputEmail")}
                                        name="email"
                                        type="email"
                                        value={email}
                                        onChange={handleInputChange}
                                        className="p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-yellow-400"
                                    />
                                </div>
                                <button
                                    type="button"
                                    onClick={checkticket}
                                    className="w-full bg-yellow-400 text-gray-700 font-bold py-2 mt-4 rounded-lg hover:bg-yellow-500 transition duration-300 uppercase"
                                >
                                    {t("checkTicket.buttonCheck")}
                                </button>
                            </div>
                        </form>
                        <div className="mt-8 flex justify-between">
                            <div className="text-center">
                                <div className="font-medium">{t("checkTicket.step1")}</div>
                                <img
                                    src="https://static.vexere.com/Files/images/longvanlimousine/check-ticket-1.png"
                                    alt="Bước 1"
                                    className="mt-2"
                                />
                            </div>
                            <div className="text-center">
                                <div className="font-medium">{t("checkTicket.step2")}</div>
                                <img
                                    src="https://static.vexere.com/Files/images/longvanlimousine/check-ticket-2.png"
                                    alt="Bước 2"
                                    className="mt-2"
                                />
                            </div>
                        </div>
                    </div>
                }
            </div>
<div className="p-4 bg-white container mx-auto">

    {!closeCheck && thongTinVe.length > 0 && (
        <>

            <div className="flex flex-col lg:flex-row ">
            {/* Left Container */}
            <div className="flex-1 p-4 bg-gray-100 rounded-md shadow-lg lg:w-8/12">
                <div className="text-center font-bold text-xl mb-6">{t("checkTicket.infoTicket")}</div>
                <div className="text-green-500 text-2xl flex items-center mb-4">
                    <svg
                        className="w-6 h-6 mr-2"
                        viewBox="64 64 896 896"
                        fill="currentColor"
                    >
                        <path d="M699 353h-46.9c-10.2 0-19.9 4.9-25.9 13.3L469 584.3l-71.2-98.8c-6-8.3-15.6-13.3-25.9-13.3H325c-6.5 0-10.3 7.4-6.5 12.7l124.6 172.8a31.8 31.8 0 0051.7 0l210.6-292c3.9-5.3.1-12.7-6.4-12.7z" />
                        <path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z" />
                    </svg>
                    {t("checkTicket.successPayment")}
                </div>
                <div className="bg-white p-4 rounded-md shadow-md">
                    <div className="mb-4">
                        <div>
                            {t("checkTicket.sendMail")}:
                            <span className="font-bold"> {thongTinVe[0].emailBooking}</span>
                        </div>
                        <div>
                            {t("checkTicket.note")}:
                            <span className="font-bold"> 0393211895</span>
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
                        <div className="mb-2">{t("checkTicket.wharf")} {thongTinVe[0].fromStationName}</div>
                        <div>
                            {t("checkTicket.pickUpTime")}:
                            <span className="font-bold">{thongTinVe[0].tripStartTime} {formatDate(thongTinVe[0].departureDate)}</span>
                        </div>
                    </div>
                    <hr className="my-4" />
                    <div>
                        <div className="font-semibold mb-2">{t("checkTicket.return")}</div>
                        <div className="mb-2">{t("checkTicket.wharf")} {thongTinVe[0].toStationName}</div>
                        <div>
                            {t("checkTicket.returnTime")}:{" "}
                            <span className="font-bold">{thongTinVe[0].tripEndtime}</span>
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
                        <div className="font-bold">{thongTinVe[0].id}</div>
                    </div>
                    <div className="mb-4">
                        <div>{t("checkTicket.seat")}:</div>
                        <div className="font-bold">{thongTinVe[0].seatsName}</div>
                    </div>
                    <div className="mb-4">
                        <div>{t("checkTicket.pickUp")}:</div>
                        <div className="font-bold">{thongTinVe[0].fromStationName}</div>
                    </div>
                    <div className="mb-4">
                        <div>{t("checkTicket.return")}:</div>
                        <div className="font-bold">{thongTinVe[0].toStationName}</div>
                    </div>
                    <div className="mb-4">
                        <div>{t("checkTicket.pickUpTime")}:</div>
                        <div className="font-bold">{thongTinVe[0].tripStartTime} {formatDate(thongTinVe[0].departureDate)} </div>
                    </div>
                    <div className="mb-4">
                        <div>{t("checkTicket.returnTime")}:</div>
                        <div className="font-bold">{thongTinVe[0].tripEndtime}</div>
                    </div>

                    <div className="mb-4">
                        <div>{t("checkTicket.ticketPrice")}: </div>
                        <div className="font-bold">{formatCurrencyVND(thongTinVe[0].totalAmount)}</div>
                    </div>
                    
                </div>
            </div>
        </div>
        </>
    )}
</div>

        </>
    );
}

export default CheckTicket;
