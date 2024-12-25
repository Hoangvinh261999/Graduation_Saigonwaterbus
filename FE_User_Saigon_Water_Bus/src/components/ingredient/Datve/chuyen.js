import React, {useState, useMemo, useRef, useEffect} from "react";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import ChiTietChuyen from "./tabthongtinchitiet";
import MultiStepForm from "./QuyTrinhDatVe/allstep";
import apiService from "../../../services/tripservice";
import './custom-datepicker.css';
import {useTranslation} from "react-i18next";

const selectChuyen = [];

function ChuyenTau() {
    const { t } = useTranslation();
    const [seatLabels, setSeatLabels] = useState([])

    const timGhe = async (event, chuyenId) => {
        if (event) {
            event.preventDefault();
        }
        try {
            const responseData = await apiService.timGhe(chuyenId);
            setSeatLabels(responseData)
        } catch (error) {

            console.error('Error fetching seat labels:', error);
        }
    }

    const [startDate, setStartDate] = useState(new Date());
//fetch api
    const [listChuyen, setListChuyen] = useState([])
    const [message1, setMessage1] = useState('')

    const fromRef = useRef();
    const toRef = useRef();

    const year = startDate.getFullYear();
    const month = String(startDate.getMonth() + 1).padStart(2, '0'); // Lưu ý rằng tháng trong JavaScript là từ 0 đến 11
    const day = String(startDate.getDate()).padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;

    const timChuyen = async (event) => {
        // event.preventDefault();
        if (event) {
            event.preventDefault();
        }
        const searchParams = {
            from: fromRef.current.value,
            to: toRef.current.value,
            departDate: formattedDate
        };
        try {
            const data = await apiService.timChuyen(searchParams);
            selectChuyen.push(data)
            setListChuyen(data);
            setMessage1('');

        } catch (error) {
            setMessage1(t("chuyen.message1"));
            setListChuyen([]);
        }
    }


//tab chit iet chuyen
    const [openTab, setOpenTab] = useState(false);
    const [selectedOption, setSelectedOption] = useState(t("chuyen.earliest") );
    const [openSeat, setOpenSeat] = useState({});

    const options = [
        t("chuyen.earliest"),
        t("chuyen.lastest")

    ];

    const sortedChuyen = useMemo(() => {
        let sortedList = [...listChuyen];
        switch (selectedOption) {
            case 'Giờ đi sớm nhất':
                sortedList.sort((a, b) => new Date('1970/01/01 ' + a.departureTime) - new Date('1970/01/01 ' + b.departureTime));
                break;
            case 'Giờ đi muộn nhất':
                sortedList.sort((a, b) => new Date('1970/01/01 ' + b.departureTime) - new Date('1970/01/01 ' + a.departureTime));
                break;

            default:
                break;
        }
        return sortedList;
    }, [listChuyen, selectedOption]);
    const handleSwap = () => {
        setSearchParams(prevParams => ({
            ...prevParams,
            from: toValue,
            to: fromValue
        }));

        const temp = fromValue;
        setFromValue(toValue);
        setToValue(temp);
        fromRef.current.value = toValue;
        toRef.current.value = temp;
    };
    const [fromValue, setFromValue] = useState('');
    const [toValue, setToValue] = useState('');
    const [searchParams, setSearchParams] = useState({
        from: '',
        to: '',
        departDate: '2024-07-05' // Ví dụ về ngày đi
    });
    const [bookedSeats, setBookedSeats] = useState({}); // State to store booked seats for each trip

    useEffect(() => {
        const fetchBookedSeats = async (id, departureDate) => {
            if (!id || !departureDate) return; // Check if id and departureDate are valid

            try {
                const response = await apiService.timGheBooked(id, departureDate);
                const length = response.length; // Assuming response is an array
                setBookedSeats(prev => ({...prev, [id]: length}));
            } catch (error) {
                console.error("Error fetching booked seats", error);
            }
        };

        sortedChuyen.forEach(chuyen => {
            fetchBookedSeats(chuyen.id, chuyen.departureDate);
        });
    }, [sortedChuyen]);


    const handleButtonClick = (event) => {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của button
        handleSwap(); // Gọi hàm swap khi click vào button
    };
    const handleChangeFrom = () => {
        setFromValue(fromRef.current.value);
    };

    const handleChangeTo = () => {
        setToValue(toRef.current.value);
    };

    return (
        <div className="mx-auto container p-0 z-10">
            <div className="flex justify-center content-center">
                <span className="font-bold text-sm md:text-3xl text-red-500 "> {message1}</span>
            </div>
            <form
                autoComplete="off"
                className="w-full text-sm md:text-base mt-2"
                onSubmit={timChuyen}
            >
                <div className="search-box-content flex flex-wrap gap-1 md:gap-4 items-end">
                    <div className="flex-1 flex flex-col w-full md:w-auto relative">
                        <label className="block text-gray-700 font-bold md:text-start">{t("chuyen.from")}</label>
                        <div className="flex items-center relative">
                            <select
                                className="appearance-none w-full p-2 border border-gray-300 rounded"
                                id="inputFrom"
                                ref={fromRef}
                                onChange={handleChangeFrom}
                            >
                                <option value="nơi đi" selected disabled hidden>{t("chuyen.fromInput")}</option>
                                <option value="1"> Bạch Đằng</option>
                                <option value="2"> Bình An</option>
                                <option value="3"> Thanh Đa</option>
                                <option value="4"> Hiệp Bình Chánh</option>
                                <option value="5"> Linh Đông</option>
                            </select>
                            <img
                                decoding="async"
                                src="//static.vexere.com/webnx/prod/img/from-v5.svg"
                                alt=""
                                className="h-8 absolute right-3 pointer-events-none hidden md:flex"
                            />
                        </div>
                    </div>

                    <button onClick={handleButtonClick} className="flex-shrink-0">
                        <img className="swap-area w-9 md:w-auto" decoding="async" src="//static.vexere.com/webnx/prod/img/swap-v3.svg"
                             alt=""/>
                    </button>

                    <div className="flex-1 flex flex-col w-full md:w-auto relative">
                        <label className="block text-gray-700 font-bold">{t("chuyen.to")}</label>
                        <div className="flex items-center relative">
                            <select
                                className="appearance-none w-full p-2 border  border-gray-300 rounded"
                                id="inputTo"
                                ref={toRef}
                                onChange={handleChangeTo}
                            >
                                <option value="nơi đến" selected disabled hidden>{t("chuyen.toInput")}</option>
                                <option value="1"> Bạch Đằng</option>
                                <option value="2"> Bình An</option>
                                <option value="3"> Thanh Đa</option>
                                <option value="4"> Hiệp Bình Chánh</option>
                                <option value="5"> Linh Đông</option>
                            </select>
                            <img
                                decoding="async"
                                src="//static.vexere.com/webnx/prod/img/to-v5.svg"
                                alt=""
                                className="h-8 absolute right-3 pointer-events-none hidden md:flex"
                            />
                        </div>
                    </div>

                    <div className="flex-1 flex flex-col w-full relative ">
                        <label className="block text-gray-700 md:hidden font-bold">Ngày KH</label>
                        <label className="hidden text-gray-700 md:block font-bold">{t("chuyen.departureDate")}</label>
                        <div className="relative w-full p-2   border rounded border-gray-300">
                            <DatePicker
                                selected={startDate}
                                onChange={(date) => setStartDate(date)}
                                className="w-full datepicker-no-focus"
                                dateFormat="dd/MM/yyyy"
                                minDate={new Date()}
                                popperPlacement="top-end"
                                popperClassName="z-10"
                            />
                            <img
                                decoding="async"
                                src="//static.vexere.com/webnx/prod/img/date-v5.svg"
                                alt=""
                                className="h-6 absolute right-2 top-2  hidden md:flex"
                            />
                        </div>
                    </div>

                    <div className="flex w-full md:w-auto">
                        <button
                            className="bg-yellow-500 text-black p-1  md:py-2 md:px-4 rounded hover:bg-yellow-600 flex items-center"
                            type="submit"
                        >
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                fill="none"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                                className="w-6 h-6 mr-2"
                            >
                                <path
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                    d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z"
                                />
                            </svg>
                            <span>{t("chuyen.findTicket")}</span>
                        </button>
                    </div>
                </div>
            </form>
            <div className="flex items-center space-x-4 my-2 text-sm md:text-base">
                <span className="font-semibold">{t("chuyen.orderBy")}:</span>
                {listChuyen && options.map(option => (
                    <button
                        key={option}
                        className={`md:p-4 p-1 rounded transition ${selectedOption === option ? 'bg-blue-500 text-white' : 'bg-gray-100 text-gray-700 hover:bg-gray-200'}`}
                        onClick={() => setSelectedOption(option)}
                    >
                        {option}
                    </button>
                ))}
            </div>
            {sortedChuyen.map(chuyen => {
                const now = new Date();
                const departureDate = new Date(chuyen.departureDate);
                const departureTime = new Date(`${chuyen.departureDate}T${chuyen.departureTime}`);
                const shouldShowAllSeats = departureDate.toDateString() === now.toDateString() &&
                    (departureTime - now) <= 15 * 60 * 1000; // chuyển đổi phút sang mili giây
                return (
                    <div key={chuyen.id}
                         className="block border rounded-lg border-b border-gray-300 shadow-lg mb-2 p-2 text-sm 2xl:text-base">
                        <div
                            className="bg-white w-full pageChuyen gap-2 flex flex-col lg:flex-row container mx-auto mb-4">
                            <div className="w-full lg:w-3/12">
                                <img className="w-full h-full object-cover rounded-lg max-h-44 p-1" alt=""
                                     src="/img/chuyentau.jpeg"/>
                            </div>
                            <div className="w-full lg:w-9/12  ">
                                <div className="flex flex-col lg:flex-row w-full justify-between">
                                    <div className="px-4 w-full">
                                        <span className="font-bold text-blue-700">{t("chuyen.seat75")}</span>
                                        <div className="flex items-center mb-2">
                                            <div className="ghe1 px-2">
                                                <svg className="TicketPC__LocationRouteSVG-sc-1mxgwjh-4 dSQflF"
                                                     xmlns="http://www.w3.org/2000/svg" width="14" height="74"
                                                     viewBox="0 0 14 74">
                                                    <path fill="none" stroke="#787878" stroke-linecap="round"
                                                          stroke-width="2" stroke-dasharray="0 7" d="M7 13.5v46"></path>
                                                    <g fill="none" stroke="#484848" stroke-width="3">
                                                        <circle cx="7" cy="7" r="7" stroke="none"></circle>
                                                        <circle cx="7" cy="7" r="5.5"></circle>
                                                    </g>
                                                    <path
                                                        d="M7 58a5.953 5.953 0 0 0-6 5.891 5.657 5.657 0 0 0 .525 2.4 37.124 37.124 0 0 0 5.222 7.591.338.338 0 0 0 .506 0 37.142 37.142 0 0 0 5.222-7.582A5.655 5.655 0 0 0 13 63.9 5.953 5.953 0 0 0 7 58zm0 8.95a3.092 3.092 0 0 1-3.117-3.06 3.117 3.117 0 0 1 6.234 0A3.092 3.092 0 0 1 7 66.95z"
                                                        fill="#787878"></path>
                                                </svg>
                                            </div>
                                            <div className="chuyen">
                                                <h1 className="font-medium">{chuyen.departureTime} ● {chuyen.startTerminal}</h1>
                                                <span className="text-xs text-gray-500">32 {t("chuyen.minute")}</span>
                                                <h1 className="font-medium">{chuyen.arrivalTime} ● {chuyen.endTerminal}</h1>
                                            </div>
                                        </div>
                                        <div className="gap-1">
                                            <div className="flex items-center cursor-pointer">
                                                <span className="font-medium px-2">{t("chuyen.detail")}</span>
                                                <button onClick={() => {
                                                    setOpenTab(prevState => ({
                                                        ...prevState,
                                                        [chuyen.id]: !prevState[chuyen.id]
                                                    }));
                                                    // handleClearStorage();
                                                }}>
                                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none"
                                                         viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor"
                                                         className="size-6">
                                                        <path strokeLinecap="round" strokeLinejoin="round"
                                                              d="m19.5 8.25-7.5 7.5-7.5-7.5"/>
                                                    </svg>
                                                </button>
                                            </div>
                                            <h1 className="text-sm text-gray-600">{t("chuyen.belongTo")} {chuyen.departureDate} {chuyen.fromTerminal} - {chuyen.toTerminal}</h1>
                                        </div>
                                    </div>
                                    <div
                                        className="flex flex-col md:items-end justify-between p-2 w-full ">
                                        <span className="text-base 2xl:text-base font-bold text-blue-600">15,000đ/ {t("chuyen.ticket")}</span>
                                        {shouldShowAllSeats ? (
                                            <div>
                                                <span
                                                    className="font-bold flex text-red-500 rounded transition text-sm">{t("chuyen.soldOut")}</span>
                                            </div>
                                        ) : (
                                            <div className="contents text-sm 2xl:text-base">
                                                {t("chuyen.available")}: {chuyen.availableSeats - (bookedSeats[chuyen.id] || 0)}
                                                <button
                                                    onClick={(event) => {
                                                        // localStorage.clear();
                                                        setSeatLabels([]);
                                                        timGhe(event, chuyen.id);
                                                        setOpenTab(false);
                                                        setOpenSeat(prevState => ({
                                                            ...prevState,
                                                            [chuyen.id]: !prevState[chuyen.id]
                                                        }));
                    
                                                    }}
                                                    className="bg-blue-500  hover:bg-blue-700 w-auto text-white font-bold p-2 rounded transition mt-2 lg:mt-0"
                                                >
                                                    {openSeat[chuyen.id] ? t("chuyen.close") : t("chuyen.chooseSeat")}
                                                </button>
                                            </div>
                                        )}
                                    </div>
                                </div>
                            </div>
                        </div>
                        {openSeat[chuyen.id] && <MultiStepForm chuyenTau={chuyen} seatLabels={seatLabels}/>}
                        <div className="bg-white">
                            {openTab[chuyen.id] && <ChiTietChuyen/>}
                        </div>
                    </div>

                );
            })}

        </div>
    );
}

export default ChuyenTau;
