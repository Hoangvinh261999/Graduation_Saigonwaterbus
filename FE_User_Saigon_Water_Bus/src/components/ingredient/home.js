import React, { useState, useEffect } from 'react';
import { MagnifyingGlassIcon, UserIcon, CreditCardIcon, CheckBadgeIcon, PlusCircleIcon } from '@heroicons/react/24/solid';
import 'react-datepicker/dist/react-datepicker.css';
import '../ingredient/Datve/datve.css';
import { useTranslation } from 'react-i18next';
const removeVietnameseTones = (str) => {
    return str
        .normalize('NFD')
        .replace(/[\u0300-\u036f]/g, '')
        .replace(/đ/g, 'd')
        .replace(/Đ/g, 'D')
        .replace(/\s/g, '')
        .replace(/[^\w\s]/gi, '');
};
const Home = () => {
    const { t } = useTranslation();

    const stations = [
        { name: 'Bạch Đằng', description: t('home.subBachDang'), image: 'https://saigonwaterbus.com/wp-content/uploads/2022/06/home-slide-0-1-1536x880.jpg' },
        { name: 'Bình An', description: t('home.subBinhAn'), image: 'https://saigonwaterbus.com/wp-content/uploads/2022/06/home-slide-3-1536x880.jpg' },
        { name: 'Thanh Đa', description: t('home.subThanhDa'), image: 'https://saigonwaterbus.com/wp-content/uploads/2022/06/home-slide-6.jpg' },
        { name: 'Hiệp Bình Chánh', description: t('home.subHiepBinhChanh'), image: 'https://saigonwaterbus.com/wp-content/uploads/2022/06/home-slide-1-1536x880.jpg' },
        { name: 'Linh Đông', description: t('home.subLinhDong'), image: 'https://saigonwaterbus.com/wp-content/uploads/2022/06/home-slide-4-1536x880.jpg' },
    ];

    const [selectedStation, setSelectedStation] = useState(stations[0]);

    useEffect(() => {
        setSelectedStation({
            ...stations[0],
            description: t('home.subBachDang'),
        });
    }, [t]);

    return (
        <div className='text-sm lg:text-base'>
            <div className="qodef-m-inner"></div>
            <div className='BookingSteps'>
                <div className="flex flex-col space-y-8 container mx-auto p-2">
                    <div className='flex items-center'>
                        <h1 className="text-base md:text-2xl font-bold">{t('home.4step')}</h1>
                        <a href='/dat-ve' alt='datve' className='ml-auto text-base md:text-2xl  font-bold bg-sky-400 text-white py-2 px-4 rounded hover:bg-sky-500'>
                            {t('home.bookTicketNow')}
                        </a>
                    </div>
                    <div className="flex space-x-8 justify-center">
                        <div className="flex flex-col items-center space-y-4">
                            <div className="bg-yellow-400 p-4 rounded-full">
                                <MagnifyingGlassIcon className="w-8 h-8"/>
                            </div>
                            <div className="text-center">
                                <h2 className="font-semibold">{t('home.findTrip')}</h2>
                                <p>{t('home.findTripNote')}</p>
                            </div>
                        </div>
                        <div className="flex flex-col items-center space-y-4">
                            <div className="bg-yellow-400 p-4 rounded-full">
                                <UserIcon className="w-8 h-8"/>
                            </div>
                            <div className="text-center">
                                <h2 className="font-semibold">{t('home.chooseSeat')}</h2>
                                <p>{t('home.chooseSeatNote')}</p>
                            </div>
                        </div>
                        <div className="flex flex-col items-center space-y-4">
                            <div className="bg-yellow-400 p-4 rounded-full">
                                <CreditCardIcon className="w-8 h-8"/>
                            </div>
                            <div className="text-center">
                                <h2 className="font-semibold">{t('home.reservation')}</h2>
                                <p>{t('home.reservationNote')}</p>
                            </div>
                        </div>
                        <div className="flex flex-col items-center space-y-4">
                            <div className="bg-yellow-400 p-4 rounded-full">
                                <CheckBadgeIcon className="w-8 h-8"/>
                            </div>
                            <div className="text-center">
                                <h2 className="font-semibold">{t('home.onShip')}</h2>
                                <p>{t('home.onShipNote')}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className='HighlightSection'>
                <div className="bg-white p-2">
                    <p className="text-blue-500 font-medium mb-4">
                        {t('note')}
                    </p>
                    <div className="flex">
                        <div className="w-1/2 pr-4">
                            <h2 className="text-2xl font-bold mb-4 leading-tight">
                                {t('home.explore')}
                            </h2>
                            <p className="mb-4 text-gray-700">
                                {t('home.exploreDetail')}
                            </p>
                            <button className="flex items-center space-x-2 text-yellow-500 font-semibold">
                                <PlusCircleIcon className="w-6 h-6"/>
                                <span>{t('home.exploreAction')}</span>
                            </button>
                        </div>
                        <div className="w-1/2">
                            <img
                                src="https://saigonwaterbus.com/wp-content/uploads/2022/06/home-slide-1-1536x880.jpg"
                                alt="Saigon Waterbus"
                                className="rounded-lg shadow-lg"
                            />
                        </div>
                    </div>
                </div>
            </div>
            <div className='Station'>
                <div className="container mx-auto px-4 py-8">
                    <h1 className="text-2xl font-bold mb-4">{t('home.systemWharf')}</h1>
                    <div className="flex space-x-2 mb-4 border-b border-gray-300">
                        {stations.map((station, index) => (
                            <button
                                key={index}
                                className={`py-2 px-4 ${selectedStation.name === station.name ? 'border-b-2 border-yellow-400 text-yellow-400' : 'text-gray-500'}`}
                                onClick={() => setSelectedStation({
                                    ...station,
                                    description: t(`home.sub${removeVietnameseTones(station.name)}`)                                })}
                            >
                                {station.name}
                            </button>
                        ))}
                    </div>
                    <div className="flex flex-col md:flex-row space-y-4 md:space-y-0 md:space-x-4">
                        <img
                            src={selectedStation.image}
                            alt={selectedStation.name}
                            className="w-full h-full md:w-1/2 max-w-full max-h-full rounded-lg shadow-lg object-cover"
                        />
                        <div className="w-full md:w-1/2">
                            <h2 className="text-xl font-bold mb-2">{selectedStation.name}</h2>
                            <p className="mb-4">{selectedStation.description}</p>
                            <button className="flex items-center space-x-2 text-yellow-500 font-semibold">
                                <PlusCircleIcon className="w-6 h-6" />
                                <span>{t('home.subAction')}</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div className='Reviewer p-2'>
                <div className="max-w-full h-128">
                    <div className="relative bg-white shadow-lg rounded-lg overflow-hidden w-full ">
                        <img
                            src="https://staging.saigonwaterbus.com/wp-content/uploads/2022/10/SWB-2.jpg"
                            alt="Background"
                            className="w-full h-128 object-cover"
                        />
                        <div className="absolute inset-0 bg-black bg-opacity-50 flex flex-col justify-center px-6 py-4">
                            <div className="flex items-center mb-4">
                                <img
                                    src="https://saigonwaterbus.com/wp-content/uploads/2022/06/linh-le.jpg"
                                    alt="Avatar"
                                    className="w-12 h-12 rounded-full mr-4 border-2 border-white"
                                />
                                <div>
                                    <p className="text-white text-lg font-bold">Linh Lê</p>
                                    <p className="text-white text-sm">Khách du lịch</p>
                                </div>
                            </div>
                            <p className="lg:w-6/12 px-1 text-white text-2xl md:text-3xl lg:text-4xl leading-tight font-semibold">
                                “{t('home.reviewerQuote')}”
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Home;
